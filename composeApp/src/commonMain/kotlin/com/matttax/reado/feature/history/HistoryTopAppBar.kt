package com.matttax.reado.feature.history

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import com.matttax.reado.images.IcArrowBack

@Composable
internal fun HistoryTopAppBar(
  onBack: () -> Unit,
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
      Box(
        modifier = Modifier
          .size(16.dp)
          .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = onBack,
          ),
      ) {
        Image(
          imageVector = IcArrowBack,
          contentDescription = null,
          modifier = Modifier.size(16.dp),
        )
      }
      Text(
        text = "History",
        color = HeaderIconTint,
        style = TextStyle(
          fontFamily = FontFamily.Serif,
          fontStyle = FontStyle.Italic,
          fontSize = 24.sp,
          lineHeight = 32.sp,
          letterSpacing = (-0.6).sp,
        ),
      )
      Box(
        modifier = Modifier
          .size(40.dp)
          .clip(CircleShape)
          .border(
            width = 1.dp,
            color = CardHairline,
            shape = CircleShape,
          ),
      )
    }
  }
}
