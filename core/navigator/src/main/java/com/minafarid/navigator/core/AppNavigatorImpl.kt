package com.minafarid.navigator.core

import androidx.navigation.NavOptionsBuilder
import com.minafarid.navigator.event.NavigatorEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

class AppNavigatorImpl() : AppNavigator {
    private val navigationEvents = Channel<NavigatorEvent>()

    override fun navigateUp(): Boolean =
        navigationEvents.trySend(NavigatorEvent.NavigateUp).isSuccess

    override fun popBackStack() {
        navigationEvents.trySend(NavigatorEvent.PopBackStack)
    }

    override fun navigate(destination: String, builder: NavOptionsBuilder.() -> Unit): Boolean =
        navigationEvents.trySend(NavigatorEvent.Directions(destination, builder)).isSuccess

    override val destinations: Flow<NavigatorEvent>
        get() = navigationEvents.receiveAsFlow()
}