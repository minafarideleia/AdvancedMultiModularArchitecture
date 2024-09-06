package com.minafarid.advancedmultimodulararchitecture.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.minafarid.home.homeScreen
import com.minafarid.login.presentation.view.LoginScreen
import com.minafarid.navigator.core.AppNavigator
import com.minafarid.navigator.desinations.HomeDestination
import com.minafarid.navigator.desinations.LoginDestination
import com.minafarid.navigator.desinations.NavigationDestination
import com.minafarid.navigator.desinations.SignUpDestination
import com.minafarid.signup.SignUpScreen

private val composableDestinations: Map<NavigationDestination, @Composable (AppNavigator, NavHostController) -> Unit> =
  mapOf(
    SignUpDestination() to { _, _ -> SignUpScreen() },
    HomeDestination to { _, navHostController -> homeScreen(navHostController) },
    LoginDestination() to { appNavigator, _ -> LoginScreen(appNavigator = appNavigator) },
  )

fun NavGraphBuilder.addComposableDestinations(
  appNavigator: AppNavigator,
  navHostController: NavHostController,
) {
  composableDestinations.forEach { entry ->
    val destination = entry.key
    composable(
      route = destination.route(),
      arguments = destination.arguments,
      deepLinks = destination.deepLinks,
    ) {
      entry.value(appNavigator, navHostController)
    }
  }
}
