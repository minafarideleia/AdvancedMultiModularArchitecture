package com.minafarid.advancedmultimodulararchitecture;

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.minafarid.advancedmultimodulararchitecture.ui.theme.AdvancedMultiModularArchitectureTheme
import com.minafarid.navigator.core.AppNavigator
import com.minafarid.navigator.event.NavigatorEvent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RoutingActivity : ComponentActivity() {

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
                            destination = event.destination, builder = event.builder
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

        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

        }
    }
}