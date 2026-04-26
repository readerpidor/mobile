package com.matttax.reado.navigation

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.matttax.reado.navigation.components.AccountComponent
import com.matttax.reado.navigation.components.HistoryComponent
import com.matttax.reado.navigation.components.HomeComponent
import com.matttax.reado.navigation.components.LoginComponent
import com.matttax.reado.navigation.components.ReadingComponent

interface RootComponent {

  val stack: Value<ChildStack<*, Child>>

  sealed interface Child {
    data class Login(val component: LoginComponent) : Child
    data class Account(val component: AccountComponent) : Child
    data class Home(val component: HomeComponent) : Child
    data class History(val component: HistoryComponent) : Child
    data class Reading(val component: ReadingComponent) : Child
  }
}
