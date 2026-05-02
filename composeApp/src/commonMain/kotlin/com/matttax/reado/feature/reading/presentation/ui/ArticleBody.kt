package com.matttax.reado.feature.reading.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp

@Composable
internal fun ArticleBody(textChunks: List<String>) {
  Column(
    modifier = Modifier.fillMaxWidth(),
    verticalArrangement = Arrangement.spacedBy(16.dp),
  ) {
    textChunks.forEach { chunk ->
      BodyParagraph(
        modifier = Modifier.fillMaxWidth(),
        text = AnnotatedString(chunk),
      )
    }
  }
}
