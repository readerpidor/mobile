package com.matttax.reado.feature.home.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matttax.reado.feature.home.presentation.ui.AiCommandInput
import com.matttax.reado.feature.home.presentation.ui.BackgroundBlob
import com.matttax.reado.feature.home.presentation.ui.HomeTopAppBar

@Composable
fun HomeScreen(
  modifier: Modifier = Modifier,
  onProfileClick: () -> Unit = {},
  onHistoryClick: () -> Unit = {},
  onSubmit: (String) -> Unit = {},
) {
  var inputValue by remember { mutableStateOf("") }
  Box(
    modifier = modifier
      .fillMaxSize()
      .background(HomeBg),
  ) {
    BackgroundBlob(
      modifier = Modifier
        .align(Alignment.Center)
        .fillMaxSize(),
    )
    Column(
      modifier = Modifier
        .fillMaxSize()
        .windowInsetsPadding(WindowInsets.statusBars)
        .padding(top = 64.dp, bottom = 48.dp)
        .padding(horizontal = 24.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center,
    ) {
      Column(
        modifier = Modifier.widthIn(max = 768.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(48.dp),
      ) {
        Text(
          text = "Paste your article link here",
          color = HeadingColor,
          textAlign = TextAlign.Center,
          style = TextStyle(
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Normal,
            fontSize = 32.sp,
            lineHeight = 45.sp,
            letterSpacing = (-0.9).sp,
          ),
        )
        AiCommandInput(
          value = inputValue,
          onValueChange = { inputValue = it },
          onSubmit = { onSubmit(inputValue) },
        )
        Spacer(Modifier.height(32.dp))
      }
    }
    HomeTopAppBar(
      onProfileClick = onProfileClick,
      onHistoryClick = onHistoryClick,
      modifier = Modifier
        .align(Alignment.TopStart)
        .fillMaxWidth(),
    )
  }
}

@Composable
@Preview
private fun HomeScreenPreview() {
  HomeScreen()
}
