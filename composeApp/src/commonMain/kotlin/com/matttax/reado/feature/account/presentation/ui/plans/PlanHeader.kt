package com.matttax.reado.feature.account.presentation.ui.plans

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun PlanHeader(
  label: String,
  labelColor: Color,
  name: String,
  nameColor: Color,
  price: String,
  priceColor: Color,
  suffixColor: Color,
) {
  Column {
    Text(
      text = label,
      color = labelColor,
      style = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 1.2.sp,
      ),
    )
    Spacer(Modifier.height(8.dp))
    Text(
      text = name,
      color = nameColor,
      style = TextStyle(
        fontFamily = FontFamily.Serif,
        fontStyle = FontStyle.Italic,
        fontSize = 36.sp,
        lineHeight = 40.sp,
      ),
    )
    Spacer(Modifier.height(16.dp))
    Row(verticalAlignment = Alignment.Bottom) {
      Text(
        text = price,
        color = priceColor,
        style = TextStyle(
          fontFamily = FontFamily.SansSerif,
          fontWeight = FontWeight.Bold,
          fontSize = 30.sp,
          lineHeight = 36.sp,
        ),
      )
      Spacer(Modifier.size(4.dp))
      Text(
        text = "/ month",
        color = suffixColor,
        style = TextStyle(
          fontFamily = FontFamily.SansSerif,
          fontWeight = FontWeight.Normal,
          fontSize = 14.sp,
          lineHeight = 20.sp,
        ),
        modifier = Modifier.padding(bottom = 4.dp),
      )
    }
  }
}
