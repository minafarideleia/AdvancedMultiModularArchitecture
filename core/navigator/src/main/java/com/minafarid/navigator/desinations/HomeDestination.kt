package com.minafarid.navigator.desinations

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

const val USER_PARAM = "user"
const val USER_AGE = "age"
const val USER_FULLNAME = "fullName"
const val HOME_ROUTE = "HomeRoute"

object HomeDestination : NavigationDestination {

    fun createHome(user: String, fullName: String, age: Int): String =
        "$HOME_ROUTE/$user/$fullName/$age"

    override fun route(): String = Screens.HomeScreenRoute.route

    override val arguments: List<NamedNavArgument>
        get() = listOf(
            navArgument(USER_PARAM) { type = NavType.StringType },
            navArgument(USER_AGE) { type = NavType.IntType },
            navArgument(USER_FULLNAME) { type = NavType.StringType },
        )
}