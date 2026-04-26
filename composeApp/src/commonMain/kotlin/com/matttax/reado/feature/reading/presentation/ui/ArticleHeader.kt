package com.matttax.reado.feature.reading.presentation.ui

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
import com.matttax.reado.feature.reading.presentation.ui.screen.DotColor
import com.matttax.reado.feature.reading.presentation.ui.screen.EyebrowReading
import com.matttax.reado.feature.reading.presentation.ui.screen.HeaderPrimary

@Composable
internal fun ArticleHeader() {
  Column(
    modifier = Modifier.fillMaxWidth(),
    verticalArrangement = Arrangement.spacedBy(24.dp),
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
      Text(
        text = "ARCHITECTURE",
        color = EyebrowReading,
        style = TextStyle(
          fontFamily = FontFamily.SansSerif,
          fontWeight = FontWeight.Bold,
          fontSize = 16.sp,
          lineHeight = 24.sp,
          letterSpacing = 1.6.sp,
        ),
      )
      Text(
        text = "•",
        color = DotColor,
        style = TextStyle(
          fontFamily = FontFamily.Serif,
          fontSize = 16.sp,
          lineHeight = 24.sp,
        ),
      )
      Text(
        text = "8 min read",
        color = EyebrowReading,
        style = TextStyle(
          fontFamily = FontFamily.SansSerif,
          fontWeight = FontWeight.Normal,
          fontSize = 16.sp,
          lineHeight = 24.sp,
        ),
      )
    }
    Text(
      text = "The Silent Symphony of Brutalist Forms",
      color = BodyPrimary,
      style = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        lineHeight = 39.6.sp,
      ),
    )
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
            text = "AR",
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
            text = "Alexander Russo",
            color = BodyPrimary,
            style = TextStyle(
              fontFamily = FontFamily.SansSerif,
              fontWeight = FontWeight.Bold,
              fontSize = 14.sp,
              lineHeight = 20.sp,
            ),
          )
          Text(
            text = "Published October 24, 2023",
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
      PlayFab()
    }
  }
}
