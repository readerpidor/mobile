package com.matttax.reado.feature.reading.presentation.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matttax.reado.common.ui.images.IcPauseBottom
import com.matttax.reado.common.ui.images.IcPlayBottom
import com.matttax.reado.feature.reading.presentation.ui.screen.BodyMutedR
import com.matttax.reado.feature.reading.presentation.ui.screen.BottomBarBg

@Composable
internal fun FloatingAiBar(
  isPlaying: Boolean,
  onPlayPauseClick: () -> Unit,
  modifier: Modifier = Modifier,
) {
  Card(
    modifier = modifier,
    shape = CircleShape,
    colors = CardDefaults.cardColors(containerColor = BottomBarBg),
    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(9.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween,
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(start = 16.dp),
      ) {
        Waveform()
        Text(
          text = "Reading...",
          color = BodyMutedR,
          style = TextStyle(
            fontFamily = FontFamily.Serif,
            fontStyle = FontStyle.Italic,
            fontSize = 14.sp,
            lineHeight = 20.sp,
          ),
        )
      }
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(end = 4.dp),
      ) {
        MiniCircleButton(onClick = onPlayPauseClick) {
          AnimatedContent(
            targetState = isPlaying,
            transitionSpec = {
              (scaleIn() + fadeIn()) togetherWith (scaleOut() + fadeOut())
            },
            label = "play-pause-icon",
          ) { playing ->
            Image(
              imageVector = if (playing) IcPauseBottom else IcPlayBottom,
              contentDescription = null,
              modifier = Modifier.size(12.dp),
            )
          }
        }
      }
    }
  }
}
