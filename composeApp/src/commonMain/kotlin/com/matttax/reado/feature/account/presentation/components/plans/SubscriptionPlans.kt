package com.matttax.reado.feature.account.presentation.components.plans

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.matttax.reado.feature.account.domain.PlanState
import com.matttax.reado.feature.account.presentation.HeadingDark
import com.matttax.reado.feature.account.presentation.ProgressTrack

@Composable
internal fun SubscriptionPlans(
  plansState: PlanState,
  onCurrentPlan: () -> Unit,
) {
  val pagerState = rememberPagerState(
    pageCount = { plansState.plans.size },
    initialPage = plansState.selected - 1
  )
  Column(
    modifier = Modifier.fillMaxSize(),
  ) {
    HorizontalPager(
      modifier = Modifier
        .fillMaxHeight(0.7f)
        .fillMaxWidth(),
      contentPadding = PaddingValues(horizontal = 16.dp),
      pageSpacing = 16.dp,
      verticalAlignment = Alignment.Top,
      state = pagerState,
      key = { page -> plansState.plans[page].name },
    ) { page ->
      val isSelected = plansState.selected - 1 == page
      PlanCard(
        plan = plansState.plans[page],
        isSelected = isSelected,
        onCtaClick = onCurrentPlan,
      )
    }
    SubscriptionPagerDots(
      modifier = Modifier.fillMaxWidth(),
      pageCount = plansState.plans.size,
      currentPage = pagerState.currentPage,
    )
  }
}

@Composable
private fun SubscriptionPagerDots(
  currentPage: Int,
  pageCount: Int,
  modifier: Modifier = Modifier,
) {
  Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.CenterVertically,
  ) {
    repeat(pageCount) { index ->
      val selected = index == currentPage
      Box(
        modifier = Modifier
          .padding(horizontal = 4.dp)
          .size(if (selected) 8.dp else 6.dp)
          .clip(CircleShape)
          .background(
            if (selected) HeadingDark else ProgressTrack
          ),
      )
    }
  }
}
