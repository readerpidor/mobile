package com.matttax.reado.feature.reading.presentation.ui

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.matttax.reado.common.ui.images.IcPause
import com.matttax.reado.common.ui.images.IcPlay
import com.matttax.reado.feature.reading.presentation.ui.screen.FabShadow
import com.matttax.reado.feature.reading.presentation.ui.screen.PlayFabBg

private const val ANIM_DURATION_MS = 300

@Composable
internal fun PlayFab(isPlaying: Boolean, onClick: () -> Unit) {
  Box(
    modifier = Modifier
      .size(56.dp)
      .clip(CircleShape)
      .background(color = PlayFabBg)
      .border(width = 0.dp, color = FabShadow, shape = CircleShape)
      .clickable(onClick = onClick),
    contentAlignment = Alignment.Center,
  ) {
    val rotation by animateFloatAsState(
      targetValue = if (isPlaying) 180f else 0f,
      animationSpec = tween(durationMillis = ANIM_DURATION_MS),
      label = "play-pause-rotation",
    )
    Crossfade(
      targetState = isPlaying,
      animationSpec = tween(durationMillis = ANIM_DURATION_MS),
      label = "play-pause-crossfade",
      modifier = Modifier.graphicsLayer { rotationZ = rotation },
    ) { playing ->
      Image(
        imageVector = if (playing) IcPause else IcPlay,
        contentDescription = null,
        modifier = Modifier.size(width = 11.dp, height = 14.dp),
      )
    }
  }
}
