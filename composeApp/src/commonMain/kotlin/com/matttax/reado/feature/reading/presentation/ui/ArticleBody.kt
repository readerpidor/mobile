package com.matttax.reado.feature.reading.presentation.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString

@Composable
internal fun ArticleBody(text: String) {
  BodyParagraph(
    modifier = Modifier.fillMaxWidth(),
    text = AnnotatedString(text),
  )
}
