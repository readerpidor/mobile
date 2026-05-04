package com.matttax.reado.feature.reading.presentation.ui.components.header

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matttax.reado.feature.reading.presentation.ui.screen.AvatarBg
import com.matttax.reado.feature.reading.presentation.ui.screen.BodyMutedR
import com.matttax.reado.feature.reading.presentation.ui.screen.BodyPrimary
import com.matttax.reado.feature.reading.presentation.ui.screen.HeaderPrimary
import kotlinx.datetime.LocalDate

@Composable
internal fun ArticleByline(
  authorName: String,
  publicationDate: LocalDate,
  isPlaying: Boolean,
  onPlayPauseClick: () -> Unit,
) {
  val initials = authorName.trim().split(Regex("\\s+"))
    .filter { it.isNotEmpty() }
    .let { words ->
      if (words.size >= 2) "${words[0].first().uppercaseChar()}${words[1].first().uppercaseChar()}"
      else words.firstOrNull()?.first()?.uppercaseChar()?.toString().orEmpty()
    }
  val publicationLabel = "Published ${
    formatPublicationDate(
      publicationDate
    )
  }"
  Row(
    modifier = Modifier.fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween,
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
      Box(
        modifier = Modifier
          .size(48.dp)
          .clip(CircleShape)
          .background(AvatarBg),
        contentAlignment = Alignment.Center,
      ) {
        Text(
          text = initials,
          color = HeaderPrimary,
          textAlign = TextAlign.Center,
          style = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            lineHeight = 24.sp,
          ),
        )
      }
      Column {
        Text(
          text = authorName,
          color = BodyPrimary,
          style = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            lineHeight = 20.sp,
          ),
        )
        Text(
          text = publicationLabel,
          color = BodyMutedR,
          style = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 16.sp,
          ),
        )
      }
    }
    PlayFab(
      isPlaying = isPlaying,
      onClick = onPlayPauseClick
    )
  }
}

private fun formatPublicationDate(date: LocalDate): String {
  val month = date.month.name.lowercase().replaceFirstChar { it.uppercase() }
  return "$month ${date.day}, ${date.year}"
}
