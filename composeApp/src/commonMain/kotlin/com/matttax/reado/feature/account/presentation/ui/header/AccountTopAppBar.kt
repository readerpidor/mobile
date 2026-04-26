package com.matttax.reado.feature.account.presentation.ui.header

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matttax.reado.feature.account.presentation.ui.screen.HeaderTint
import com.matttax.reado.common.ui.images.IcArrowBack

@Composable
internal fun AccountTopAppBar(
  onBack: () -> Unit,
  modifier: Modifier = Modifier,
) {
  Box(
    modifier = modifier
      .background(
        color = Color(0xFFF8FAFC).copy(alpha = 0.92f)
      ),
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .windowInsetsPadding(WindowInsets.statusBars)
        .padding(horizontal = 24.dp, vertical = 16.dp)
        .clickable(
          interactionSource = remember { MutableInteractionSource() },
          indication = null,
          onClick = onBack,
        ),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
      Image(
        modifier = Modifier.size(16.dp),
        imageVector = IcArrowBack,
        contentDescription = null,
      )
      Text(
        text = "Back",
        color = HeaderTint,
        style = TextStyle(
          fontFamily = FontFamily.SansSerif,
          fontWeight = FontWeight.Medium,
          fontSize = 16.sp,
          lineHeight = 24.sp,
        ),
      )
    }
  }
}
