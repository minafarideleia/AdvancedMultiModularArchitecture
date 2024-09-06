package com.minafarid.navigator.desinations

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink

interface NavigationDestination {
  fun route(): String

  val arguments: List<NamedNavArgument> get() = emptyList()

  val deepLinks: List<NavDeepLink> get() = emptyList()
}
