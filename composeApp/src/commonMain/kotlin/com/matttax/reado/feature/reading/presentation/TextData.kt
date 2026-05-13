package com.matttax.reado.feature.reading.presentation

data class TextData(
  val text: String,
  val textTypes: List<TextType>,
)

sealed interface TextType {
  data object Default : TextType
  data class Header(val level: Int) : TextType
  data class Bold(val start: Int, val end: Int) : TextType
  data class Italic(val start: Int, val end: Int) : TextType
  data class BoldItalic(val start: Int, val end: Int) : TextType
  data class Code(val start: Int, val end: Int) : TextType
  data class Link(val start: Int, val end: Int, val url: String) : TextType
}
