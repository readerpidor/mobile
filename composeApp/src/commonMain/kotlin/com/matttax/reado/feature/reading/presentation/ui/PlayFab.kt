package com.matttax.reado.feature.reading.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.matttax.reado.feature.reading.presentation.ui.screen.FabShadow
import com.matttax.reado.feature.reading.presentation.ui.screen.PlayFabBg
import com.matttax.reado.common.ui.images.IcPlay

@Composable
internal fun PlayFab() {
  Box(
    modifier = Modifier
      .size(56.dp)
      .clip(CircleShape)
      .background(color = PlayFabBg)
      .border(width = 0.dp, color = FabShadow, shape = CircleShape),
    contentAlignment = Alignment.Center,
  ) {
    Image(
      imageVector = IcPlay,
      contentDescription = null,
      modifier = Modifier.size(width = 11.dp, height = 14.dp),
    )
  }
}
