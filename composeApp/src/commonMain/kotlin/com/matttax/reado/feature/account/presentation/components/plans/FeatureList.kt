package com.matttax.reado.feature.account.presentation.components.plans

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun FeatureList(
  features: List<String>,
  imageVector: ImageVector,
  textColor: Color,
) {
  Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
    features.forEach { feature ->
      Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.Top,
      ) {
        Image(
          imageVector = imageVector,
          contentDescription = null,
          modifier = Modifier
            .padding(top = 2.dp)
            .size(16.dp),
        )
        Text(
          text = feature,
          color = textColor,
          style = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 20.sp,
          ),
        )
      }
    }
  }
}
