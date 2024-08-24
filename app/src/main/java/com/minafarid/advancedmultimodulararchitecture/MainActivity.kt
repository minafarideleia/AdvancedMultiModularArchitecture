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

  lateinit var appSettingDataStore: DataStore<AppSettings>

  @Inject
  lateinit var sessionDataStoreInterface: SessionDataStoreInterface

  @Inject
  lateinit var preferencesDataStoreInterface: PreferencesDataStoreInterface

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
            appSettingDataStore,
            Modifier.padding(innerPadding),
            sessionDataStoreInterface,
            preferencesDataStoreInterface,
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
  appSettingDataStore: DataStore<AppSettings>,
  modifier: Modifier,
  sessionDataStoreInterface: SessionDataStoreInterface,
  preferencesDataStoreInterface: PreferencesDataStoreInterface,
) {
  val scope = rememberCoroutineScope()
  val appSettings by appSettingDataStore.data.collectAsState(initial = AppSettings())
  val accessTokenFlow by sessionDataStoreInterface.getAccessTokenFlow()
    .collectAsState(initial = "")
  var accessTokenValue by remember { mutableStateOf("Loading...") }

  val languageFlow by preferencesDataStoreInterface.getLanguageFlow().collectAsState(initial = "")
  var languageValue by remember { mutableStateOf("Loading...") }

  Column(modifier = Modifier.padding(50.dp)) {
    // display saved language
    Spacer(modifier = Modifier.height(16.dp))
    Text(text = "Access Token Flow: $accessTokenFlow")
    Spacer(modifier = Modifier.height(16.dp))
    Text(text = "Language Flow: $languageFlow")

    LaunchedEffect(Unit) {
      scope.launch {
        accessTokenValue = sessionDataStoreInterface.getAccessToken()
        languageValue = preferencesDataStoreInterface.getLanguage()
      }
    }
    Spacer(modifier = Modifier.height(16.dp))

    Text(text = "Access Token Value: $accessTokenValue")
    Spacer(modifier = Modifier.height(16.dp))
    Text(text = "Language Value: $languageValue")
    Spacer(modifier = Modifier.height(16.dp))

    Button(onClick = {
      scope.launch {
        sessionDataStoreInterface.setAccessToken("access token " + System.currentTimeMillis())
        preferencesDataStoreInterface.setLanguage("language " + System.currentTimeMillis())
      }
    }) {
      Text(text = "Insert")
    }
    Spacer(modifier = Modifier.height(16.dp))

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
