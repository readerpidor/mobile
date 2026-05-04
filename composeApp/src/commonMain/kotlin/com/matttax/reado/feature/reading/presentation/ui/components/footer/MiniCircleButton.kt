package com.matttax.reado.feature.reading.presentation.ui.components.footer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.matttax.reado.feature.reading.presentation.ui.screen.BottomIconBg

@Composable
internal fun MiniCircleButton(
  onClick: () -> Unit,
  content: @Composable () -> Unit,
) {
  Box(
    modifier = Modifier
      .size(40.dp)
      .clip(CircleShape)
      .background(BottomIconBg)
      .clickable(onClick = onClick),
    contentAlignment = Alignment.Center,
  ) {
    content()
  }
}
