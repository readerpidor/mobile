package com.matttax.reado.feature.reading.presentation

object MarkdownBalancer {

  // Longest-first so e.g. `***` is matched before `**`.
  private val DELIMITERS = listOf("***", "___", "**", "__", "~~", "`")

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
        val match = DELIMITERS.firstOrNull { original.startsWith(it, i) }
        if (match != null) {
          if (openStack.lastOrNull() == match) {
            openStack.removeAt(openStack.lastIndex)
          } else {
            openStack.add(match)
          }
          sb.append(match)
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
}
