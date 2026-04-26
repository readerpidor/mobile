package com.matttax.reado.feature.home.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.matttax.reado.common.ui.images.IcHistory
import com.matttax.reado.common.ui.images.IcUserAvatarCircle

@Composable
internal fun HomeTopAppBar(
  onProfileClick: () -> Unit,
  onHistoryClick: () -> Unit,
  modifier: Modifier = Modifier,
) {
  Box(
    modifier = modifier.background(Color(0xFFF8FAFC).copy(alpha = 0.92f)),
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .windowInsetsPadding(WindowInsets.statusBars)
        .padding(horizontal = 24.dp, vertical = 16.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween,
    ) {
      TopBarIconButton(
        icon = IcUserAvatarCircle,
        onClick = onProfileClick,
      )
      TopBarIconButton(
        icon = IcHistory,
        onClick = onHistoryClick,
      )
    }
  }
}
