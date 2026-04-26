package com.matttax.reado.data.network.serializers

import com.matttax.reado.data.model.common.Timestamp
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object TimestampSerializer : KSerializer<Timestamp> {
  override val descriptor: SerialDescriptor =
    PrimitiveSerialDescriptor("google.protobuf.Timestamp", PrimitiveKind.STRING)

  override fun serialize(encoder: Encoder, value: Timestamp) {
    encoder.encodeString(formatRfc3339(value))
  }

  override fun deserialize(decoder: Decoder): Timestamp {
    return parseRfc3339(decoder.decodeString())
  }

  private fun formatRfc3339(value: Timestamp): String {
    val whole = value.seconds.toEpochUtcParts()
    val base = "${whole.year.pad(4)}-${whole.month.pad(2)}-${whole.day.pad(2)}T" +
      "${whole.hour.pad(2)}:${whole.minute.pad(2)}:${whole.second.pad(2)}"
    val nanos = if (value.nanos == 0) "" else ".${value.nanos.toString().padStart(9, '0').trimEnd('0')}"
    return "${base}${nanos}Z"
  }

  private fun parseRfc3339(input: String): Timestamp {
    val trimmed = input.trim().removeSuffix("Z")
    val dotIdx = trimmed.indexOf('.')
    val (datePart, fracPart) = if (dotIdx >= 0) {
      trimmed.substring(0, dotIdx) to trimmed.substring(dotIdx + 1)
    } else {
      trimmed to ""
    }
    val (date, time) = datePart.split('T')
    val (y, mo, d) = date.split('-').map { it.toInt() }
    val (h, mi, s) = time.split(':').map { it.toInt() }
    val seconds = utcToEpochSeconds(y, mo, d, h, mi, s)
    val nanos = if (fracPart.isEmpty()) 0
      else fracPart.padEnd(9, '0').take(9).toInt()
    return Timestamp(seconds, nanos)
  }

  private fun Int.pad(width: Int) = toString().padStart(width, '0')

  private data class UtcParts(
    val year: Int, val month: Int, val day: Int,
    val hour: Int, val minute: Int, val second: Int,
  )

  private fun Long.toEpochUtcParts(): UtcParts {
    var days = this / 86_400L
    var rem = this % 86_400L
    if (rem < 0) { rem += 86_400L; days -= 1 }
    val hour = (rem / 3600L).toInt()
    val minute = ((rem % 3600L) / 60L).toInt()
    val second = (rem % 60L).toInt()
    var (year, month, day) = daysToYmd(days)
    return UtcParts(year, month, day, hour, minute, second)
  }

  private fun utcToEpochSeconds(y: Int, mo: Int, d: Int, h: Int, mi: Int, s: Int): Long {
    val days = ymdToDays(y, mo, d)
    return days * 86_400L + h * 3600L + mi * 60L + s
  }

  private fun isLeap(y: Int) = (y % 4 == 0 && y % 100 != 0) || y % 400 == 0

  private val daysInMonth = intArrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

  private fun ymdToDays(y: Int, m: Int, d: Int): Long {
    var days = 0L
    if (y >= 1970) {
      for (yr in 1970 until y) days += if (isLeap(yr)) 366 else 365
    } else {
      for (yr in y until 1970) days -= if (isLeap(yr)) 366 else 365
    }
    for (mo in 1 until m) {
      days += daysInMonth[mo - 1]
      if (mo == 2 && isLeap(y)) days += 1
    }
    days += d - 1
    return days
  }

  private fun daysToYmd(daysFromEpoch: Long): Triple<Int, Int, Int> {
    var d = daysFromEpoch
    var year = 1970
    if (d >= 0) {
      while (true) {
        val len = if (isLeap(year)) 366 else 365
        if (d < len) break
        d -= len; year++
      }
    } else {
      while (d < 0) {
        year--
        val len = if (isLeap(year)) 366 else 365
        d += len
      }
    }
    var month = 1
    while (month <= 12) {
      var len = daysInMonth[month - 1].toLong()
      if (month == 2 && isLeap(year)) len += 1
      if (d < len) break
      d -= len; month++
    }
    return Triple(year, month, (d + 1).toInt())
  }
}
