package com.matttax.reado.feature.reading.presentation.ui.components.body

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matttax.reado.feature.reading.presentation.TextType
import com.matttax.reado.feature.reading.presentation.ui.components.body.ArticleBodySpecs.DefaultFontSizeSp
import com.matttax.reado.feature.reading.presentation.ui.components.body.ArticleBodySpecs.DefaultLevel
import com.matttax.reado.feature.reading.presentation.ui.components.body.ArticleBodySpecs.HeaderLevelStepSp
import com.matttax.reado.feature.reading.presentation.ui.components.body.ArticleBodySpecs.HeaderLineHeightMultiplier
import com.matttax.reado.feature.reading.presentation.ui.components.body.ArticleBodySpecs.HeaderTopPaddingBaseDp
import com.matttax.reado.feature.reading.presentation.ui.components.body.ArticleBodySpecs.HeaderTopPaddingStepDp
import com.matttax.reado.feature.reading.presentation.ui.components.body.ArticleBodySpecs.LineHeightMultiplier

fun TextType.fontSize(): TextUnit = when (this) {
  is TextType.Header -> {
    val clamped = level.coerceIn(1, 6)
    (DefaultFontSizeSp + (DefaultLevel - clamped) * HeaderLevelStepSp).sp
  }
  is TextType.Default -> DefaultFontSizeSp.sp
}

fun TextType.fontWeight(): FontWeight = when(this) {
  is TextType.Header -> FontWeight.Bold
  is TextType.Default -> FontWeight.Normal
}

fun TextType.lineHeightMultiplier(): Float = when (this) {
  is TextType.Header -> HeaderLineHeightMultiplier
  is TextType.Default -> LineHeightMultiplier
}

fun TextType.verticalPadding(): Dp = when (this) {
  is TextType.Header -> {
    val clamped = level.coerceIn(1, 6)
    (HeaderTopPaddingBaseDp + (DefaultLevel - clamped) * HeaderTopPaddingStepDp)
      .coerceAtLeast(0f).dp
  }
  is TextType.Default -> 0.dp
}
