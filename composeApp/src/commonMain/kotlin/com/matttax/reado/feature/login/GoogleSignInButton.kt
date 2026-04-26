package com.matttax.reado.feature.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matttax.reado.images.IcArrowForward
import com.matttax.reado.images.IcGoogle
import org.jetbrains.compose.resources.stringResource
import reado.composeapp.generated.resources.Res
import reado.composeapp.generated.resources.login_sign_in_with_google

@Composable
internal fun GoogleSignInButton(onClick: () -> Unit) {
  Surface(
    modifier = Modifier
      .fillMaxWidth()
      .clip(
        shape = RoundedCornerShape(24.dp)
      )
      .clickable(
        onClick = onClick,
      ),
    color = Color.White,
    shape = RoundedCornerShape(24.dp),
    shadowElevation = 18.dp,
    tonalElevation = 18.dp,
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween,
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
      ) {
        Box(
          modifier = Modifier.size(40.dp),
          contentAlignment = Alignment.Center,
        ) {
          Image(
            imageVector = IcGoogle,
            contentDescription = null,
            modifier = Modifier.size(20.dp),
          )
        }
        Text(
          text = stringResource(Res.string.login_sign_in_with_google),
          color = ButtonText,
          style = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            lineHeight = 24.sp,
          ),
        )
      }
      Image(
        imageVector = IcArrowForward,
        contentDescription = null,
        modifier = Modifier.size(16.dp),
      )
    }
  }
}
