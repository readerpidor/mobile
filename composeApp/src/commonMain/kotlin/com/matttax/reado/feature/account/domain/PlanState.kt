package com.matttax.reado.feature.account.domain

data class PlanState(
  val plans: List<Plan>,
  val selected: Int,
)
