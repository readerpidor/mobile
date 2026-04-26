package com.matttax.reado.feature.account.presentation.ui.stats

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matttax.reado.feature.account.presentation.ui.screen.BodyMuted
import com.matttax.reado.feature.account.presentation.ui.screen.HeadingDark
import com.matttax.reado.feature.account.presentation.ui.screen.StatSurfaceB
import com.matttax.reado.common.ui.images.IcCycle

@Composable
internal fun NextCycleCard() {
  StatCard(background = StatSurfaceB) {
    StatHeader(
      imageVector = IcCycle,
      iconSize = 22.dp,
      label = "NEXT CYCLE",
    )
    Spacer(Modifier.height(32.dp))
    Text(
      text = "Nov 14, 2024",
      color = HeadingDark,
      style = TextStyle(
        fontFamily = FontFamily.Serif,
        fontStyle = FontStyle.Italic,
        fontSize = 36.sp,
        lineHeight = 40.sp,
      ),
    )
    Spacer(Modifier.height(8.dp))
    Text(
      text = "Scheduled renewal of your Pro plan",
      color = BodyMuted.copy(alpha = 0.7f),
      style = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
      ),
    )
    Spacer(Modifier.height(8.dp))
    Text(
      text = "Standard billing applies via Visa ending in 4492",
      color = BodyMuted.copy(alpha = 0.5f),
      style = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
      ),
    )
  }
}
