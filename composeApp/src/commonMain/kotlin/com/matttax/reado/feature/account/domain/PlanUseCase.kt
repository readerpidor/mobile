package com.matttax.reado.feature.account.domain

internal class PlanUseCase {

  operator fun invoke() =
    PlanState(
      plans = listOf(EssentialPlan, StandardPlan, EnterprisePlan),
      selected = 2,
    )

  companion object {
    private val EssentialPlan = Plan(
      label = "ESSENTIAL",
      name = "Light",
      price = Price(12),
      features = listOf(
        "50 AI Syntheses / month",
        "Standard Reading Mode",
        "2 Device Sync",
      ),
      ctaLabel = "Downgrade",
    )
    private val StandardPlan = Plan(
      label = "MOST POPULAR",
      name = "Pro",
      price = Price(29),
      features = listOf(
        "Unlimited AI Insights",
        "Priority Neural Processing",
        "Custom Editorial Style",
      ),
      ctaLabel = "Current Plan",
      statusBadge = "ACTIVE",
    )
    private val EnterprisePlan = Plan(
      label = "ENTERPRISE",
      name = "Unlimited",
      price = Price(59),
      features = listOf(
        "Shared Workspace",
        "API Direct Access",
        "Dedicated Account Lead",
      ),
      ctaLabel = "Upgrade Now",
    )
  }
}
