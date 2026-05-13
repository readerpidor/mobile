package com.matttax.reado.feature.reading.presentation

object MarkdownStripper {

  private val HEADER_PREFIX = Regex("""^\s*(#{1,6})\s+""")

  fun strip(text: String): TextData {
    val headerMatch = HEADER_PREFIX.find(text)
    val textType = if (headerMatch != null) {
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
    s = removeEmphasis(s)
    s = removeStrikethrough(s)
    s = unescape(s)
    return TextData(text = s, textType = textType)
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

  private fun removeEmphasis(s: String): String {
    var r = s
    r = Regex("""\*\*\*([^*]+)\*\*\*""").replace(r, "$1")
    r = Regex("""___([^_]+)___""").replace(r, "$1")
    r = Regex("""\*\*([^*]+)\*\*""").replace(r, "$1")
    r = Regex("""__([^_]+)__""").replace(r, "$1")
    r = Regex("""\*([^*\n]+)\*""").replace(r, "$1")
    // Underscore italic guarded against snake_case_words
    r = Regex("""(?<![\w_])_([^_\n]+)_(?![\w_])""").replace(r, "$1")
    return r
  }

  private fun removeStrikethrough(s: String): String =
    Regex("""~~([^~]+)~~""").replace(s, "$1")

  private fun unescape(s: String): String =
    Regex("""\\([\\`*_{}\[\]()#+\-.!>~|])""").replace(s, "$1")
}
