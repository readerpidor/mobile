package com.matttax.reado.data.network.serializers

import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@OptIn(ExperimentalEncodingApi::class)
object Base64ByteArraySerializer : KSerializer<ByteArray> {
  override val descriptor: SerialDescriptor =
    PrimitiveSerialDescriptor("Base64ByteArray", PrimitiveKind.STRING)

  override fun serialize(encoder: Encoder, value: ByteArray) {
    encoder.encodeString(Base64.encode(value))
  }

  override fun deserialize(decoder: Decoder): ByteArray {
    return Base64.decode(decoder.decodeString())
  }
}
