package com.matttax.reado.feature.account.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.matttax.reado.feature.account.presentation.ui.header.AccountTopAppBar
import com.matttax.reado.feature.account.presentation.ui.header.ProfileHeader
import com.matttax.reado.feature.account.presentation.ui.plans.SubscriptionPlans
import com.matttax.reado.feature.account.domain.PlanUseCase

@Composable
fun AccountScreen(
  modifier: Modifier = Modifier,
  onBack: () -> Unit = {},
  onCurrentPlan: () -> Unit = {},
) {
  val plansState = remember { PlanUseCase().invoke() }
  Box(
    modifier = modifier
      .fillMaxSize()
      .background(color = PageBg),
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .windowInsetsPadding(WindowInsets.statusBars)
        .padding(
          top = 64.dp,
          bottom = 48.dp,
        ),
      verticalArrangement = Arrangement.spacedBy(48.dp),
    ) {
      ProfileHeader(
        modifier = Modifier.padding(horizontal = 24.dp),
        userName = "Maksim Romanov"
      )
      SubscriptionPlans(
        plansState = plansState,
        onCurrentPlan = onCurrentPlan,
      )
    }
    AccountTopAppBar(
      modifier = Modifier
        .align(Alignment.TopStart)
        .fillMaxWidth(),
      onBack = onBack,
    )
  }
}

@Composable
@Preview
private fun AccountScreenPreview() {
  AccountScreen()
}
