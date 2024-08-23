package com.minafarid.advancedmultimodulararchitecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.minafarid.advancedmultimodulararchitecture.ui.theme.AdvancedMultiModularArchitectureTheme
import com.minafarid.datastore.settings.AppSettings
import com.minafarid.datastore.settings.AppSettingsSerializer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class MainActivity : ComponentActivity() {
    lateinit var appSettingsDataStore: DataStore<AppSettings>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appSettingsDataStore = DataStoreFactory.create(
            serializer = AppSettingsSerializer(),
            produceFile = { dataStoreFile("app_settings.json") },
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
        )

        // val room = Room.databaseBuilder()
        enableEdgeToEdge()
        setContent {
            AdvancedMultiModularArchitectureTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }

        val counter = 100

        println(counter)
    }

    fun mainMainMainMainMainMainMainMainMainMainMainMainMainMain() {
    }
}

@Composable
@Suppress("FunctionNaming")
fun Greeting(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = "Base Url: ${BuildConfig.BASE_URL}!",
            modifier = modifier,
        )
        Text(
            text = "DB Version: ${BuildConfig.DB_VERSION}!",
            modifier = modifier,
        )
        Text(
            text = "Can Clear Cache: ${BuildConfig.CAN_CLEAR_CACHE}!",
            modifier = modifier,
        )
        Text(
            text = "Map Key: ${BuildConfig.MAP_KEY}!",
            modifier = modifier,
        )
    }
}

@Preview(showBackground = true)
@Composable
@Suppress("FunctionNaming")
fun GreetingPreview() {
    AdvancedMultiModularArchitectureTheme {
        Greeting()
    }
}
