package com.matttax.reado.feature.reading.presentation.ui.components.body

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matttax.reado.feature.reading.presentation.TextData
import com.matttax.reado.feature.reading.presentation.TextType
import com.matttax.reado.feature.reading.presentation.ui.components.body.ArticleBodySpecs.DefaultFontSizeSp
import com.matttax.reado.feature.reading.presentation.ui.components.body.ArticleBodySpecs.DefaultLevel
import com.matttax.reado.feature.reading.presentation.ui.components.body.ArticleBodySpecs.HeaderLevelStepSp
import com.matttax.reado.feature.reading.presentation.ui.components.body.ArticleBodySpecs.HeaderLineHeightMultiplier
import com.matttax.reado.feature.reading.presentation.ui.components.body.ArticleBodySpecs.HeaderTopPaddingBaseDp
import com.matttax.reado.feature.reading.presentation.ui.components.body.ArticleBodySpecs.HeaderTopPaddingStepDp
import com.matttax.reado.feature.reading.presentation.ui.components.body.ArticleBodySpecs.LineHeightMultiplier
import com.matttax.reado.feature.reading.presentation.ui.components.body.ArticleBodySpecs.ListStartPaddingDp

fun TextData.paragraphType(): TextType =
  textTypes.firstOrNull {
    it is TextType.Header || it is TextType.BulletPoint || it is TextType.NumberPoint
  } ?: TextType.Default

fun TextData.toAnnotatedString() = buildAnnotatedString {
  val marker = paragraphType().listMarker()
  val offset: Int
  if (marker != null) {
    addStyle(SpanStyle(fontWeight = FontWeight.Bold), length, length + marker.length)
    append(marker)
    append("\t")
    offset = length
  } else {
    offset = 0
  }
  append(text)
  val end = this.length
  for (type in textTypes) {
    when (type) {
      is TextType.Link -> {
        val s = (type.start + offset).coerceIn(offset, end)
        val e = (type.end + offset).coerceIn(s, end)
        if (s < e) {
          addLink(
            url = LinkAnnotation.Url(
              url = type.url,
              styles = TextLinkStyles(style = LinkSpanStyle),
            ),
            start = s,
            end = e,
          )
        }
      }
      else -> {
        val style = type.spanStyle()
        val range = type.range()
        if (style != null && range != null) {
          val s = (range.first + offset).coerceIn(offset, end)
          val e = (range.second + offset).coerceIn(s, end)
          if (s < e) addStyle(style, s, e)
        }
      }
    }
  }
}

private fun TextType.listMarker(): String? = when (this) {
  is TextType.BulletPoint -> "•\t"
  is TextType.NumberPoint -> "$number.\t"
  else -> null
}

private val LinkSpanStyle = SpanStyle(
  color = Color.Blue,
  textDecoration = TextDecoration.Underline,
)

fun TextType.fontSize(): TextUnit = when (this) {
  is TextType.Header -> {
    val clamped = level.coerceIn(1, 6)
    (DefaultFontSizeSp + (DefaultLevel - clamped) * HeaderLevelStepSp).sp
  }
  else -> DefaultFontSizeSp.sp
}

fun TextType.fontWeight(): FontWeight = when (this) {
  is TextType.Header -> FontWeight.Bold
  else -> FontWeight.Normal
}

fun TextType.lineHeightMultiplier(): Float = when (this) {
  is TextType.Header -> HeaderLineHeightMultiplier
  else -> LineHeightMultiplier
}

fun TextType.verticalPadding(): Dp = when (this) {
  is TextType.Header -> {
    val clamped = level.coerceIn(1, 6)
    (HeaderTopPaddingBaseDp + (DefaultLevel - clamped) * HeaderTopPaddingStepDp)
      .coerceAtLeast(0f).dp
  }
  else -> 0.dp
}

fun TextType.startPadding(): Dp = when (this) {
  is TextType.BulletPoint, is TextType.NumberPoint -> ListStartPaddingDp.dp
  else -> 0.dp
}

private fun TextType.spanStyle(): SpanStyle? = when (this) {
  is TextType.Bold -> SpanStyle(fontWeight = FontWeight.Bold)
  is TextType.Italic -> SpanStyle(fontStyle = FontStyle.Italic)
  is TextType.BoldItalic -> SpanStyle(fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic)
  is TextType.Code -> SpanStyle(fontFamily = FontFamily.Monospace, color = Color.Gray)
  else -> null
}

private fun TextType.range(): Pair<Int, Int>? = when (this) {
  is TextType.Bold -> start to end
  is TextType.Italic -> start to end
  is TextType.BoldItalic -> start to end
  is TextType.Code -> start to end
  else -> null
}

