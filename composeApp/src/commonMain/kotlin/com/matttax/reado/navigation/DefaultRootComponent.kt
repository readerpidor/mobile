package com.matttax.reado.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DelicateDecomposeApi
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.matttax.reado.data.ReaderService
import com.matttax.reado.feature.account.presentation.DefaultAccountComponent
import com.matttax.reado.feature.history.presentation.DefaultHistoryComponent
import com.matttax.reado.feature.home.presentation.DefaultHomeComponent
import com.matttax.reado.feature.login.presentation.DefaultLoginComponent
import com.matttax.reado.feature.reading.presentation.DefaultReadingComponent
import kotlinx.serialization.Serializable

class DefaultRootComponent(
  componentContext: ComponentContext,
  private val readerService: ReaderService,
) : RootComponent, ComponentContext by componentContext {

  private val navigation = StackNavigation<Config>()

  override val stack: Value<ChildStack<*, RootComponent.Child>> = childStack(
    source = navigation,
    serializer = Config.serializer(),
    initialConfiguration = Config.Home,
    handleBackButton = true,
    childFactory = ::child,
  )

  @OptIn(DelicateDecomposeApi::class)
  private fun child(config: Config, context: ComponentContext): RootComponent.Child = when (config) {
    Config.Login -> RootComponent.Child.Login(
      DefaultLoginComponent(
        componentContext = context,
        onSignedIn = { navigation.bringToFront(Config.Account) },
      )
    )
    Config.Account -> RootComponent.Child.Account(
      DefaultAccountComponent(
        componentContext = context,
        onBack = { navigation.pop() },
        onCurrentPlan = { navigation.bringToFront(Config.Home) },
      )
    )
    Config.Home -> RootComponent.Child.Home(
      DefaultHomeComponent(
        componentContext = context,
        onProfileClick = { navigation.push(Config.Account) },
        onHistoryClick = { navigation.push(Config.History) },
        onSubmit = { url -> navigation.push(Config.Reading(url)) },
      )
    )
    Config.History -> RootComponent.Child.History(
      DefaultHistoryComponent(
        componentContext = context,
        onBack = { navigation.pop() },
      )
    )
    is Config.Reading -> RootComponent.Child.Reading(
      DefaultReadingComponent(
        componentContext = context,
        readerService = readerService,
        url = config.url,
        onBack = { navigation.pop() },
      )
    )
  }

  @Serializable
  private sealed interface Config {
    @Serializable data object Login : Config
    @Serializable data object Account : Config
    @Serializable data object Home : Config
    @Serializable data object History : Config
    @Serializable data class Reading(val url: String) : Config
  }
}
