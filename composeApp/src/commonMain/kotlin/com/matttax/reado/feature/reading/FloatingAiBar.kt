package com.matttax.reado.feature.reading

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matttax.reado.images.IcSkip
import com.matttax.reado.images.IcSpeaker

@Composable
internal fun FloatingAiBar(modifier: Modifier = Modifier) {
  Box(
    modifier = modifier
      .clip(CircleShape)
      .background(BottomBarBg)
      .border(
        width = 1.dp,
        color = TopBarHairline,
        shape = CircleShape,
      )
      .padding(9.dp),
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
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
        MiniCircleButton {
          Image(
            imageVector = IcSpeaker,
            contentDescription = null,
            modifier = Modifier.size(12.dp),
          )
        }
        MiniCircleButton {
          Image(
            imageVector = IcSkip,
            contentDescription = null,
            modifier = Modifier.size(11.dp),
          )
        }
      }
    }
  }
}
