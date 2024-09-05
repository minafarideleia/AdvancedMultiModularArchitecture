package com.minafarid.navigator.desinations

import androidx.navigation.NamedNavArgument
const val LOGIN_ROUTE = "LoginRoute"

class LoginDestination : NavigationDestination {
    override fun destination(): String =  Screens.LoginScreenRoute.route

    override val arguments: List<NamedNavArgument>
        get() = listOf() // pass any argument needed
}