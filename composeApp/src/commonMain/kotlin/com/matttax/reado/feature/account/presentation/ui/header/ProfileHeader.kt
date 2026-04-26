package com.matttax.reado.feature.account.presentation.ui.header

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
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

@Composable
internal fun ProfileHeader(
  userName: String,
  modifier: Modifier = Modifier,
) {
  Column(
    modifier = modifier.fillMaxWidth(),
    verticalArrangement = Arrangement.spacedBy(16.dp),
  ) {
    Text(
      text = userName,
      color = HeadingDark,
      style = TextStyle(
        fontFamily = FontFamily.Serif,
        fontStyle = FontStyle.Italic,
        fontSize = 36.sp,
        lineHeight = 45.sp,
      ),
    )
    Text(
      text = "Curating your intellectual workspace since October 2023. Managing editorial insights and global synthesis.",
      color = BodyMuted.copy(alpha = 0.7f),
      style = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 28.sp,
      ),
      modifier = Modifier.widthIn(max = 448.dp),
    )
  }
}
