package com.matttax.reado.feature.reading.presentation

object MarkdownStripper {

  private val HEADER_PREFIX = Regex("""^\s*(#{1,6})\s+""")

  // Longest-first so e.g. `***` is matched before `**` and `**` before `*`.
  private val EMPHASIS_DELIMITERS = listOf("***", "___", "**", "__", "*", "_", "`")

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
    // Inline links `[text](url)` are extracted into Link spans by extractEmphasis.
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
    extractInto(input, 0, input.length, output, spans)
    return output.toString() to spans
  }

  private fun extractInto(
    input: String,
    from: Int,
    to: Int,
    output: StringBuilder,
    spans: MutableList<TextType>,
  ) {
    var i = from
    while (i < to) {
      val c = input[i]
      if (c == '\\' && i + 1 < to && input[i + 1] in ESCAPABLE) {
        output.append(input[i + 1])
        i += 2
        continue
      }
      if (c == '[') {
        val link = parseLink(input, i, to)
        if (link != null) {
          val spanStart = output.length
          extractInto(input, link.textStart, link.textEnd, output, spans)
          val spanEnd = output.length
          if (spanStart < spanEnd) {
            spans.add(TextType.Link(spanStart, spanEnd, link.url))
          }
          i = link.endIndex
          continue
        }
      }
      val delim = matchDelimiterAt(input, i)
      if (delim != null) {
        val contentStart = i + delim.length
        val closeIdx = findUnescapedDelim(input, contentStart, to, delim)
        if (closeIdx >= 0) {
          val spanStart = output.length
          appendContent(input, contentStart, closeIdx, output)
          val spanEnd = output.length
          val span: TextType? = when (delim) {
            "***", "___" -> TextType.BoldItalic(spanStart, spanEnd)
            "**", "__" -> TextType.Bold(spanStart, spanEnd)
            "*", "_" -> TextType.Italic(spanStart, spanEnd)
            "`" -> TextType.Code(spanStart, spanEnd)
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
  }

  private data class ParsedLink(
    val textStart: Int,
    val textEnd: Int,
    val url: String,
    val endIndex: Int,
  )

  private fun parseLink(input: String, openBracket: Int, bound: Int): ParsedLink? {
    var j = openBracket + 1
    while (j < bound) {
      if (input[j] == '\\' && j + 1 < bound) { j += 2; continue }
      if (input[j] == ']') break
      j++
    }
    if (j >= bound || input[j] != ']') return null
    val textEnd = j
    if (j + 1 >= bound || input[j + 1] != '(') return null
    val urlStart = j + 2
    var k = urlStart
    while (k < bound) {
      if (input[k] == '\\' && k + 1 < bound) { k += 2; continue }
      if (input[k] == ')') break
      k++
    }
    if (k >= bound || input[k] != ')') return null
    val url = input.substring(urlStart, k).trim()
    if (url.isEmpty()) return null
    return ParsedLink(openBracket + 1, textEnd, url, k + 1)
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

  private fun findUnescapedDelim(input: String, from: Int, to: Int, delim: String): Int {
    var j = from
    while (j < to) {
      if (input[j] == '\\' && j + 1 < to) {
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
