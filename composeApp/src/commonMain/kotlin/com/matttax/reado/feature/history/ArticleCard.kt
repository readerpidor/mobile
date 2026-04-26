package com.matttax.reado.feature.history

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun ArticleCard(
  article: HistoryArticle,
  onViewQA: () -> Unit,
) {
  Surface(
    modifier = Modifier.fillMaxWidth(),
    color = CardBgHistory,
    shape = RoundedCornerShape(32.dp),
    shadowElevation = 1.dp,
    border = BorderStroke(1.dp,
      CardHairline
    ),
  ) {
    Column(
      modifier = Modifier.padding(25.dp),
      verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
      Text(
        text = article.date,
        color = MetaText,
        style = TextStyle(
          fontFamily = FontFamily.SansSerif,
          fontWeight = FontWeight.Normal,
          fontSize = 12.sp,
          lineHeight = 16.sp,
          letterSpacing = 0.6.sp,
        ),
      )
      Text(
        text = article.title,
        color = HeroText,
        style = TextStyle(
          fontFamily = FontFamily.Serif,
          fontWeight = FontWeight.Normal,
          fontSize = 24.sp,
          lineHeight = 33.sp,
        ),
      )
      Spacer(
        modifier = Modifier.height(12.dp),
      )
      ProgressSection(
        progress = article.progress,
      )
      Spacer(
        modifier = Modifier.height(12.dp),
      )
      ViewQAButton(
        onClick = onViewQA,
      )
    }
  }
}
