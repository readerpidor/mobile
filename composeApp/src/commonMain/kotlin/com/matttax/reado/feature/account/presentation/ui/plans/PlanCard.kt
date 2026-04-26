package com.matttax.reado.feature.account.presentation.ui.plans

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matttax.reado.feature.account.domain.Plan
import com.matttax.reado.feature.account.presentation.ui.screen.BodyMuted
import com.matttax.reado.feature.account.presentation.ui.screen.CardBg
import com.matttax.reado.feature.account.presentation.ui.screen.HairlineStrong
import com.matttax.reado.feature.account.presentation.ui.screen.HeadingDark
import com.matttax.reado.feature.account.presentation.ui.screen.LabelMuted
import com.matttax.reado.feature.account.presentation.ui.screen.ProAccent
import com.matttax.reado.feature.account.presentation.ui.screen.ProGradientEnd
import com.matttax.reado.feature.account.presentation.ui.screen.ProGradientStart
import com.matttax.reado.feature.account.presentation.ui.screen.ProMuted
import com.matttax.reado.common.ui.images.IcCheckDark
import com.matttax.reado.common.ui.images.IcCheckLight

@Composable
internal fun PlanCard(
  plan: Plan,
  isSelected: Boolean,
  onCtaClick: () -> Unit = {},
) {
  if (isSelected) {
    ProPlanCardSurface(
      plan = plan,
      onCtaClick = onCtaClick,
    )
  } else {
    StandardPlanCard(
      plan = plan,
      onCtaClick = onCtaClick,
    )
  }
}

@Composable
private fun StandardPlanCard(
  plan: Plan,
  onCtaClick: () -> Unit,
) {
  Surface(
    modifier = Modifier.fillMaxWidth(),
    shape = RoundedCornerShape(32.dp),
    color = CardBg,
  ) {
    Column(
      modifier = Modifier.padding(32.dp),
    ) {
      PlanHeader(
        label = plan.label,
        labelColor = LabelMuted,
        name = plan.name,
        nameColor = HeadingDark,
        price = "$" + plan.price.value,
        priceColor = HeadingDark,
        suffixColor = BodyMuted.copy(
          alpha = 0.6f
        ),
      )
      Spacer(Modifier.height(32.dp))
      FeatureList(
        features = plan.features,
        imageVector = IcCheckDark,
        textColor = BodyMuted,
      )
      Spacer(Modifier.height(40.dp))
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(12.dp))
          .clickable(onClick = onCtaClick),
      ) {
        PlanCta(
          label = plan.ctaLabel,
          background = Color.Transparent,
          borderColor = HairlineStrong,
          textColor = HeadingDark,
        )
      }
    }
  }
}

@Composable
private fun ProPlanCardSurface(
  plan: Plan,
  onCtaClick: () -> Unit,
) {
  Surface(
    modifier = Modifier.fillMaxWidth(),
    color = Color.Transparent,
    shape = RoundedCornerShape(32.dp),
  ) {
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(32.dp))
        .background(
          Brush.linearGradient(
            colors = listOf(
              ProGradientStart,
              ProGradientEnd
            ),
          )
        )
        .padding(32.dp),
    ) {
      Column(
        modifier = Modifier.fillMaxWidth()
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = if (plan.statusBadge != null) {
            Arrangement.SpaceBetween
          } else {
            Arrangement.Start
          },
        ) {
          Text(
            text = plan.label,
            color = ProMuted,
            style = TextStyle(
              fontFamily = FontFamily.SansSerif,
              fontWeight = FontWeight.Bold,
              fontSize = 12.sp,
              lineHeight = 16.sp,
              letterSpacing = 1.2.sp,
            ),
          )
          plan.statusBadge?.let { badge ->
            Box(
              modifier = Modifier
                .clip(RoundedCornerShape(999.dp))
                .background(Color.White.copy(alpha = 0.2f))
                .padding(horizontal = 12.dp, vertical = 4.dp),
            ) {
              Text(
                text = badge,
                color = Color.White,
                style = TextStyle(
                  fontFamily = FontFamily.SansSerif,
                  fontWeight = FontWeight.Bold,
                  fontSize = 10.sp,
                  lineHeight = 15.sp,
                  letterSpacing = 1.sp,
                ),
              )
            }
          }
        }
        Spacer(Modifier.height(8.dp))
        Text(
          text = plan.name,
          color = Color.White,
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
            text = "$" + plan.price.value,
            color = Color.White,
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
            color = ProMuted,
            style = TextStyle(
              fontFamily = FontFamily.SansSerif,
              fontWeight = FontWeight.Normal,
              fontSize = 14.sp,
              lineHeight = 20.sp,
            ),
            modifier = Modifier.padding(bottom = 4.dp),
          )
        }
        Spacer(Modifier.height(32.dp))
        FeatureList(
          features = plan.features,
          imageVector = IcCheckLight,
          textColor = Color.White,
        )
        Spacer(Modifier.height(40.dp))
        Surface(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable(
              interactionSource = remember { MutableInteractionSource() },
              indication = null,
              onClick = onCtaClick,
            ),
          color = Color.White,
          shape = RoundedCornerShape(12.dp),
          shadowElevation = 6.dp,
        ) {
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .padding(vertical = 16.dp),
            contentAlignment = Alignment.Center,
          ) {
            Text(
              text = plan.ctaLabel,
              color = ProAccent,
              style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 16.sp,
                lineHeight = 24.sp,
              ),
            )
          }
        }
      }
    }
  }
}
