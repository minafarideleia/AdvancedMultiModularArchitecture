package com.minafarid.navigator

import androidx.navigation.NavOptionsBuilder
import com.minafarid.navigator.event.NavigatorEvent
import kotlinx.coroutines.flow.Flow

interface AppNavigator {
    fun navigateUp(): Boolean

    fun popBackStack()

    fun navigate(
        route: String,
        builder: NavOptionsBuilder.() -> Unit = { launchSingleTop = true }
    ): Boolean

    val destinations: Flow<NavigatorEvent>
}