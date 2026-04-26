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
import com.matttax.reado.feature.account.presentation.ui.screen.HeadingDark
import com.matttax.reado.feature.account.presentation.ui.screen.ProAccent
import com.matttax.reado.feature.account.presentation.ui.screen.StatSurfaceA
import com.matttax.reado.common.ui.images.IcUsage

@Composable
internal fun UsageMetricsCard() {
  StatCard(background = StatSurfaceA) {
    StatHeader(
      imageVector = IcUsage,
      iconSize = 24.dp,
      label = "USAGE METRICS",
    )
    Spacer(Modifier.height(32.dp))
    Text(
      text = "1,284",
      color = ProAccent,
      style = TextStyle(
        fontFamily = FontFamily.Serif,
        fontStyle = FontStyle.Italic,
        fontSize = 48.sp,
        lineHeight = 48.sp,
      ),
    )
    Spacer(Modifier.height(8.dp))
    Text(
      text = "Hours read this month",
      color = HeadingDark,
      style = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
      ),
    )
    Spacer(Modifier.height(16.dp))
    ProgressBar(progress = 0.72f)
  }
}
