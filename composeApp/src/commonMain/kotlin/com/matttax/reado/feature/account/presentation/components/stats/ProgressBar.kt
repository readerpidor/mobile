package com.matttax.reado.feature.account.presentation.components.stats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.matttax.reado.feature.account.presentation.ProAccent
import com.matttax.reado.feature.account.presentation.ProgressTrack

@Composable
internal fun ProgressBar(progress: Float) {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .height(6.dp)
      .clip(RoundedCornerShape(999.dp))
      .background(ProgressTrack),
  ) {
    Box(
      modifier = Modifier
        .fillMaxWidth(progress)
        .fillMaxHeight()
        .clip(RoundedCornerShape(999.dp))
        .background(ProAccent),
    )
  }
}
