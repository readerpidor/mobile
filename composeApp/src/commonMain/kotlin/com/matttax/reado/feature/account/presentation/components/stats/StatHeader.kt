package com.matttax.reado.feature.account.presentation.components.stats

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.matttax.reado.feature.account.presentation.BodyMuted

@Composable
internal fun StatHeader(
  imageVector: ImageVector,
  iconSize: Dp,
  label: String,
) {
  Row(
    modifier = Modifier.fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween,
  ) {
    Image(
      imageVector = imageVector,
      contentDescription = null,
      modifier = Modifier.size(iconSize),
    )
    Text(
      text = label,
      color = BodyMuted.copy(alpha = 0.5f),
      style = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 10.sp,
        lineHeight = 15.sp,
        letterSpacing = 2.sp,
      ),
    )
  }
}
