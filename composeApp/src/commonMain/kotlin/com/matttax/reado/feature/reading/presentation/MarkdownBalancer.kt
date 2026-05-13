package com.matttax.reado.feature.reading.presentation

object MarkdownBalancer {

  // Longest-first so e.g. `***` is matched before `**` and `**` before `*`.
  private val DELIMITERS = listOf("***", "___", "**", "__", "~~", "`", "*", "_")

  fun balance(chunks: Map<Int, String>): Map<Int, String> {
    val openStack = mutableListOf<String>()
    val result = LinkedHashMap<Int, String>()
    for (key in chunks.keys.sorted()) {
      val original = chunks[key] ?: continue
      val sb = StringBuilder()
      for (d in openStack) sb.append(d)
      var i = 0
      while (i < original.length) {
        val c = original[i]
        if (c == '\\' && i + 1 < original.length) {
          sb.append(c).append(original[i + 1])
          i += 2
          continue
        }
        val match = matchDelimiterAt(original, i)
        if (match != null) {
          when {
            openStack.lastOrNull() == match -> {
              openStack.removeAt(openStack.lastIndex)
              sb.append(match)
            }
            openStack.isNotEmpty() -> {
              // Nested markdown inside an already-open delimiter — drop the inner markers.
            }
            else -> {
              openStack.add(match)
              sb.append(match)
            }
          }
          i += match.length
          continue
        }
        sb.append(c)
        i++
      }
      for (j in openStack.indices.reversed()) sb.append(openStack[j])
      result[key] = sb.toString()
    }
    return result
  }

  private fun matchDelimiterAt(text: String, i: Int): String? {
    val match = DELIMITERS.firstOrNull { text.startsWith(it, i) } ?: return null
    return when (match) {
      "*" -> if (isAsteriskListMarker(text, i)) null else match
      "_" -> if (isUnderscoreInsideWord(text, i)) null else match
      else -> match
    }
  }

  // `* item` — `*` at line start followed by a space is a list marker, not italic.
  private fun isAsteriskListMarker(text: String, i: Int): Boolean {
    if (i + 1 >= text.length || text[i + 1] != ' ') return false
    var j = i - 1
    while (j >= 0 && text[j] != '\n') {
      if (!text[j].isWhitespace()) return false
      j--
    }
    return true
  }

  // `snake_case` — `_` adjacent to a word char on either side is part of an identifier.
  private fun isUnderscoreInsideWord(text: String, i: Int): Boolean {
    val before = if (i > 0) text[i - 1] else null
    val after = if (i + 1 < text.length) text[i + 1] else null
    val beforeIsWord = before != null && (before.isLetterOrDigit() || before == '_')
    val afterIsWord = after != null && (after.isLetterOrDigit() || after == '_')
    return beforeIsWord || afterIsWord
  }
}
