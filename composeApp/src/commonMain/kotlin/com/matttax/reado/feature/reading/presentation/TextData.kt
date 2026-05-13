package com.matttax.reado.feature.reading.presentation

data class TextData(
  val text: String,
  val textType: TextType,
)

sealed interface TextType {
  data object Default : TextType
  data class Header(val level: Int) : TextType
}
