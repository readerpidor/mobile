package com.matttax.reado.feature.reading.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matttax.reado.feature.reading.domain.model.ArticleMetadata
import com.matttax.reado.feature.reading.presentation.ui.screen.BodyPrimary

@Composable
internal fun ArticleHeader(
  metadata: ArticleMetadata,
  isPlaying: Boolean,
  onPlayPauseClick: () -> Unit,
) {
  Column(
    modifier = Modifier.fillMaxWidth(),
    verticalArrangement = Arrangement.spacedBy(24.dp),
  ) {
    ArticleEyebrow(
      articleTopic = metadata.articleTopic,
      readMinutes = metadata.readMinutes,
    )
    Text(
      text = metadata.title.trim(),
      color = BodyPrimary,
      style = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        lineHeight = 39.6.sp,
      ),
    )
    ArticleByline(
      authorName = metadata.authorName,
      publicationDate = metadata.publicationDate,
      isPlaying = isPlaying,
      onPlayPauseClick = onPlayPauseClick,
    )
  }
}
