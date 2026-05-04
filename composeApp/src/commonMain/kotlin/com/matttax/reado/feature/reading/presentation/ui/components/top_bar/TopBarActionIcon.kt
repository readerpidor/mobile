package com.matttax.reado.feature.reading.presentation.ui.components.top_bar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.matttax.reado.feature.reading.presentation.ui.screen.BodyMutedR

@Composable
internal fun TopBarActionIcon(icon: ImageVector) {
  Box(
    modifier = Modifier
      .clip(CircleShape)
      .padding(8.dp),
  ) {
    Icon(
      imageVector = icon,
      contentDescription = null,
      tint = BodyMutedR,
      modifier = Modifier.size(18.dp),
    )
  }
}
