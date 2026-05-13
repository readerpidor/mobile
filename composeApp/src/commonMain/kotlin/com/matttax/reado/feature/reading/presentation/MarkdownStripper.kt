package com.matttax.reado.feature.reading.presentation

object MarkdownStripper {

  private val HEADER_PREFIX = Regex("""^\s*(#{1,6})\s+""")

  // Longest-first so e.g. `***` is matched before `**` and `**` before `*`.
  private val EMPHASIS_DELIMITERS = listOf("***", "___", "**", "__", "*", "_")

  private val ESCAPABLE = setOf(
    '\\', '`', '*', '_', '{', '}', '[', ']', '(', ')',
    '#', '+', '-', '.', '!', '>', '~', '|',
  )

  fun strip(text: String): TextData {
    val headerMatch = HEADER_PREFIX.find(text)
    val paragraphType: TextType = if (headerMatch != null) {
      TextType.Header(level = headerMatch.groupValues[1].length)
    } else {
      TextType.Default
    }
    var s = text
    s = removeImages(s)
    s = removeLinks(s)
    s = removeAutoLinks(s)
    s = removeCodeBlocks(s)
    s = removeInlineCode(s)
    s = removeHeadings(s)
    s = removeHorizontalRules(s)
    s = removeBlockquotes(s)
    s = removeListMarkers(s)
    s = removeStrikethrough(s)
    val (cleaned, spans) = extractEmphasis(s)
    return TextData(
      text = cleaned,
      textTypes = listOf(paragraphType) + spans,
    )
  }

  private fun removeImages(s: String): String {
    var r = s
    r = Regex("""!\[[^]]*]\([^)]*\)""").replace(r, "")
    r = Regex("""!\[[^]]*]\[[^]]*]""").replace(r, "")
    return r
  }

  private fun removeLinks(s: String): String {
    var r = s
    // Inline links: [text](url)
    r = Regex("""\[[^]]*]\([^)]*\)""").replace(r, "")
    // Reference link definitions: [ref]: url
    r = Regex("""^\s*\[[^]]+]:\s*\S+.*$""", RegexOption.MULTILINE).replace(r, "")
    // Reference-style links: [text][ref]
    r = Regex("""\[[^]]*]\[[^]]*]""").replace(r, "")
    return r
  }

  private fun removeAutoLinks(s: String): String {
    var r = s
    r = Regex("""<https?://[^>\s]+>""").replace(r, "")
    r = Regex("""<[^@\s>]+@[^@\s>]+>""").replace(r, "")
    return r
  }

  private fun removeCodeBlocks(s: String): String {
    var r = s
    r = Regex("""```[^\n]*\n""").replace(r, "")
    r = r.replace("```", "")
    return r
  }

  private fun removeInlineCode(s: String): String =
    Regex("""`+([^`]+)`+""").replace(s, "$1")

  private fun removeHeadings(s: String): String {
    var r = s
    // ATX leading #s
    r = Regex("""^\s*#{1,6}\s+""", RegexOption.MULTILINE).replace(r, "")
    // ATX optional trailing #s
    r = Regex("""\s+#+\s*$""", RegexOption.MULTILINE).replace(r, "")
    return r
  }

  private fun removeHorizontalRules(s: String): String =
    // Also covers Setext heading underlines: ===, ---
    Regex("""^\s*([-*_=])\1{2,}\s*$""", RegexOption.MULTILINE).replace(s, "")

  private fun removeBlockquotes(s: String): String =
    Regex("""^\s*>+\s?""", RegexOption.MULTILINE).replace(s, "")

  private fun removeListMarkers(s: String): String {
    var r = s
    r = Regex("""^\s*\d+\.\s+""", RegexOption.MULTILINE).replace(r, "")
    r = Regex("""^\s*[-*+]\s+""", RegexOption.MULTILINE).replace(r, "")
    return r
  }

  private fun removeStrikethrough(s: String): String =
    Regex("""~~([^~]+)~~""").replace(s, "$1")

  private fun extractEmphasis(input: String): Pair<String, List<TextType>> {
    val output = StringBuilder()
    val spans = mutableListOf<TextType>()
    var i = 0
    while (i < input.length) {
      val c = input[i]
      if (c == '\\' && i + 1 < input.length && input[i + 1] in ESCAPABLE) {
        output.append(input[i + 1])
        i += 2
        continue
      }
      val delim = matchDelimiterAt(input, i)
      if (delim != null) {
        val contentStart = i + delim.length
        val closeIdx = findUnescapedDelim(input, contentStart, delim)
        if (closeIdx >= 0) {
          val spanStart = output.length
          appendContent(input, contentStart, closeIdx, output)
          val spanEnd = output.length
          val span: TextType? = when (delim) {
            "***", "___" -> TextType.BoldItalic(spanStart, spanEnd)
            "**", "__" -> TextType.Bold(spanStart, spanEnd)
            "*", "_" -> TextType.Italic(spanStart, spanEnd)
            else -> null
          }
          if (span != null) spans.add(span)
          i = closeIdx + delim.length
          continue
        }
      }
      output.append(c)
      i++
    }
    return output.toString() to spans
  }

  private fun matchDelimiterAt(text: String, i: Int): String? {
    val match = EMPHASIS_DELIMITERS.firstOrNull { text.startsWith(it, i) } ?: return null
    return when (match) {
      "_" -> if (isUnderscoreInsideWord(text, i)) null else match
      else -> match
    }
  }

  private fun isUnderscoreInsideWord(text: String, i: Int): Boolean {
    val before = if (i > 0) text[i - 1] else null
    val after = if (i + 1 < text.length) text[i + 1] else null
    val beforeIsWord = before != null && (before.isLetterOrDigit() || before == '_')
    val afterIsWord = after != null && (after.isLetterOrDigit() || after == '_')
    return beforeIsWord || afterIsWord
  }

  private fun findUnescapedDelim(input: String, from: Int, delim: String): Int {
    var j = from
    while (j < input.length) {
      if (input[j] == '\\' && j + 1 < input.length) {
        j += 2
        continue
      }
      if (input.startsWith(delim, j)) return j
      j++
    }
    return -1
  }

  private fun appendContent(input: String, from: Int, to: Int, output: StringBuilder) {
    var j = from
    while (j < to) {
      if (input[j] == '\\' && j + 1 < to && input[j + 1] in ESCAPABLE) {
        output.append(input[j + 1])
        j += 2
      } else {
        output.append(input[j])
        j++
      }
    }
  }
}
