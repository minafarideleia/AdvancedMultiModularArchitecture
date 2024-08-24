package com.minafarid.advancedmultimodulararchitecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.minafarid.advancedmultimodulararchitecture.ui.theme.AdvancedMultiModularArchitectureTheme
import com.minafarid.datastore.settings.AppSettings
import com.minafarid.datastore.settings.AppSettingsSerializer
import com.minafarid.datastore.settings.Language
import com.minafarid.datastore.settings.Location
import com.minafarid.protodatastore.manager.preferences.PreferencesDataStoreInterface
import com.minafarid.protodatastore.manager.session.SessionDataStoreInterface
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  @Inject
  lateinit var preferencesDataStoreInterface: PreferencesDataStoreInterface

  @Inject
  lateinit var sessionDataStoreInterface: SessionDataStoreInterface

  lateinit var appSettingDataStore: DataStore<AppSettings>
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    appSettingDataStore = DataStoreFactory.create(
      serializer = AppSettingsSerializer(),
      produceFile = { dataStoreFile("app_settings.json") },
      scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
    )

    // val room = Room.databaseBuilder()
    enableEdgeToEdge()
    setContent {
      AdvancedMultiModularArchitectureTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        modifier = Modifier.padding(innerPadding),
//                    )
          SettingsScreen(
            sessionDataStoreInterface,
            appSettingDataStore,
            Modifier.padding(innerPadding),
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
fun SettingsScreen(
  sessionDataStoreInterface: SessionDataStoreInterface,
  appSettingDataStore: DataStore<AppSettings>,
  modifier: Modifier,
) {
  val scope = rememberCoroutineScope()
  val appSettings by appSettingDataStore.data.collectAsState(initial = AppSettings())

  val accessTokenFlow by sessionDataStoreInterface.getAccessTokenFlow()
    .collectAsState(initial = "")

  var accessTokenValue by remember { mutableStateOf("") }

  Column(modifier = Modifier.padding(50.dp)) {
    Spacer(modifier = Modifier.height(16.dp))
    Text(text = "accessTokenFlow: $accessTokenFlow")
    Spacer(modifier = Modifier.height(16.dp))

    LaunchedEffect(Unit) {
      scope.launch {
        accessTokenValue = sessionDataStoreInterface.getAccessToken()
      }
    }

    Text(text = "accessTokenValue: $accessTokenValue")

    Spacer(modifier = Modifier.height(16.dp))

    Button(onClick = {
      scope.launch {
        sessionDataStoreInterface.setAccessToken("Access Token " + System.currentTimeMillis())
      }
    }) {
      Text(text = "Insert")
    }

    // display saved language
    Text(text = "Language: " + appSettings.language)
    Spacer(modifier = Modifier.height(16.dp))

    // display saved locations
    Text(text = "Last known locations: ")
    appSettings.lastKnownLocations.forEach { location ->
      Spacer(modifier = Modifier.height(16.dp))
      Text(text = "Lat: ${location.lat} Lng: ${location.long}")
    }
    Spacer(modifier = Modifier.height(16.dp))
    val newLocation = Location(37.123, 122.908)

    // create drop down menu to display language options and set location as well
    Language.entries.forEach { language ->
      DropdownMenuItem(text = { Text(text = language.name) }, onClick = {
        scope.launch {
          appSettingDataStore.updateData { currentSettings ->
            currentSettings.copy(
              language = language,
              lastKnownLocations = currentSettings.lastKnownLocations.add(
                newLocation,
              ),
            )
          }
        }
      })
    }
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
