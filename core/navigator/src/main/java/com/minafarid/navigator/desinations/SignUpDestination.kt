package com.minafarid.navigator.desinations

const val SIGNUP_ROUTE = "SignUpRoute"

class SignUpDestination : NavigationDestination {
  override fun route(): String = Screens.SignUpScreenRoute.route
}
