package com.minafarid.advancedmultimodulararchitecture

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.minafarid.advancedmultimodulararchitecture.ui.theme.AdvancedMultiModularArchitectureTheme
import com.minafarid.navigator.core.AppNavigator
import com.minafarid.navigator.desinations.Screens
import com.minafarid.navigator.event.NavigatorEvent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RoutingActivity : AppCompatActivity() {
    @Inject
    lateinit var appNavigator: AppNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdvancedMultiModularArchitectureTheme {
                appScaffold(appNavigator)
            }
        }
    }

    @Composable
    fun appScaffold(appNavigator: AppNavigator) {

        val navController = rememberNavController()

        LaunchedEffect(navController) {
            appNavigator.destinations.collect { event ->
                when (event) {
                    is NavigatorEvent.Directions -> {
                        appNavigator.navigate(
                            destination = event.destination,
                            builder = event.builder
                        )
                    }

                    NavigatorEvent.NavigateUp -> {
                        appNavigator.navigateUp()
                    }

                    NavigatorEvent.PopBackStack -> {
                        appNavigator.popBackStack()
                    }
                }
            }
        }


        Scaffold { pad ->
            NavHost(
                modifier = Modifier.padding(pad),
                navController = navController, startDestination = Screens.LoginScreenRoute.route,
                enterTransition = { fadeIn(animationSpec = tween(500)) },
                exitTransition = { fadeOut(animationSpec = tween(500)) }) {

            }

        }

    }
}