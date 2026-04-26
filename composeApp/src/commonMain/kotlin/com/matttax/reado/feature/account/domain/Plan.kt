package com.matttax.reado.feature.account.domain

data class Plan(
  val label: String,
  val name: String,
  val price: Price,
  val features: List<String>,
  val ctaLabel: String,
  val statusBadge: String? = null,
)
