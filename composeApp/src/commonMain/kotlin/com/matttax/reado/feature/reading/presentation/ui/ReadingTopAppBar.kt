package com.matttax.reado.feature.reading.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matttax.reado.feature.reading.presentation.ui.screen.HeaderPrimary
import com.matttax.reado.feature.reading.presentation.ui.screen.ProgressTrackR
import com.matttax.reado.common.ui.images.IcArrowBack
import com.matttax.reado.common.ui.images.IconsBookmark
import com.matttax.reado.common.ui.images.IconsMore
import com.matttax.reado.common.ui.images.IconsSearch

@Composable
internal fun ReadingTopAppBar(
  onBack: () -> Unit,
  progress: Float,
  modifier: Modifier = Modifier,
) {
  Box(
    modifier = modifier.background(Color.White.copy(alpha = 0.9f)),
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .windowInsetsPadding(WindowInsets.statusBars)
        .padding(horizontal = 24.dp, vertical = 16.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween,
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
      ) {
        Box(
          modifier = Modifier
            .clip(CircleShape)
            .clickable(
              interactionSource = remember { MutableInteractionSource() },
              indication = null,
              onClick = onBack,
            )
            .padding(8.dp),
        ) {
          Image(
            imageVector = IcArrowBack,
            contentDescription = null,
            modifier = Modifier.size(16.dp),
          )
        }
        Text(
          text = "Atelier",
          color = HeaderPrimary,
          style = TextStyle(
            fontFamily = FontFamily.Serif,
            fontStyle = FontStyle.Italic,
            fontSize = 24.sp,
            lineHeight = 32.sp,
          ),
        )
      }
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
      ) {
        TopBarActionIcon(icon = IconsSearch)
        TopBarActionIcon(icon = IconsBookmark)
        TopBarActionIcon(icon = IconsMore)
      }
    }
    Box(
      modifier = Modifier
        .align(Alignment.BottomStart)
        .fillMaxWidth()
        .height(2.dp)
        .background(ProgressTrackR),
    ) {
      Box(
        modifier = Modifier
          .fillMaxWidth(progress)
          .fillMaxHeight()
          .background(HeaderPrimary),
      )
    }
  }
}
