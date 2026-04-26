package com.matttax.reado.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matttax.reado.images.IcSendArrow

@Composable
internal fun AiCommandInput(
  value: String,
  onValueChange: (String) -> Unit,
  onSubmit: () -> Unit,
) {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .height(50.dp)
      .clip(CircleShape)
      .background(Color.White.copy(alpha = 0.9f))
      .border(
        width = 1.dp,
        color = InputBorder,
        shape = CircleShape,
      )
      .padding(9.dp),
  ) {
    Row(
      modifier = Modifier.fillMaxSize(),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
      Spacer(
        modifier = Modifier.size(8.dp),
      )
      Box(
        modifier = Modifier
          .weight(1f)
          .padding(horizontal = 12.dp),
        contentAlignment = Alignment.CenterStart,
      ) {
        if (value.isEmpty()) {
          Text(
            text = "Link or text",
            color = InputPlaceholder,
            style = TextStyle(
              fontFamily = FontFamily.SansSerif,
              fontSize = 14.sp,
              lineHeight = 20.sp,
            ),
          )
        }
        BasicTextField(
          modifier = Modifier.fillMaxWidth(),
          value = value,
          onValueChange = onValueChange,
          singleLine = true,
          textStyle = TextStyle(
            color = InputText,
            fontFamily = FontFamily.SansSerif,
            fontSize = 14.sp,
            lineHeight = 20.sp,
          ),
        )
      }
      Box(
        modifier = Modifier
          .size(width = 72.dp, height = 32.dp)
          .clip(RoundedCornerShape(6.dp))
          .border(
            width = 1.dp,
            color = InputBorder,
            shape = RoundedCornerShape(6.dp),
          ),
      )
      Spacer(
        modifier = Modifier.size(4.dp),
      )
      Box(
        modifier = Modifier
          .size(width = 48.dp, height = 32.dp)
          .clip(RoundedCornerShape(50.dp))
          .background(
            Brush.horizontalGradient(
              colors = listOf(
                GradientStart,
                GradientEnd
              ),
            )
          )
          .clickable(
            onClick = onSubmit,
          ),
        contentAlignment = Alignment.Center,
      ) {
        Image(
          imageVector = IcSendArrow,
          contentDescription = null,
          modifier = Modifier.size(12.dp),
        )
      }
    }
  }
}
