package com.minafarid.login.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextButton
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.minafarid.domain.model.toJson
import com.minafarid.login.R
import com.minafarid.login.presentation.protocol.LoginInput
import com.minafarid.login.presentation.protocol.LoginOutput
import com.minafarid.login.presentation.protocol.LoginViewState
import com.minafarid.login.presentation.viewmodel.LoginViewModel
import com.minafarid.navigator.core.AppNavigator
import com.minafarid.navigator.desinations.HomeDestination
import com.minafarid.navigator.desinations.Screens
import com.minafarid.presentation.StateRenderer
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun LoginScreen(appNavigator: AppNavigator) {
  val loginViewModel: LoginViewModel = hiltViewModel()
  val stateRenderer by loginViewModel.stateRendererFlow.collectAsState()
  // React to viewOutput events

  LaunchedEffect(loginViewModel) {
    loginViewModel.viewOutput.collect { output ->
      when (output) {
        is LoginOutput.NavigateToMain -> {
          val mainOutput = output as LoginOutput.NavigateToMain
          appNavigator.navigate(
            HomeDestination.createHome(
              user = mainOutput.user.toJson(),
              age = 36,
              fullName = mainOutput.user.fullName,
            ),
          )
        }

        is LoginOutput.NavigateToRegister -> {
          appNavigator.navigate(Screens.SignUpScreenRoute.route)
        }

        is LoginOutput.ShowError -> {
          TODO()
        }
      }
    }
  }

  // State Renderer

  StateRenderer.of(statRenderer = stateRenderer, retryAction = { loginViewModel.login() }) {
    onUiState { updatedState ->
      ScreeUiContent(updatedState, loginViewModel)
    }
    onLoadingState { _ ->
      // ScreeUiContent(updatedState, loginViewModel)
    }
    onSuccessState { user ->
      val encodedUserJson = URLEncoder.encode(user.toJson(), StandardCharsets.UTF_8.toString())
      appNavigator.navigate(
        HomeDestination.createHome(
          user = encodedUserJson,
          age = 36,
          fullName = user.fullName,
        ),
      )
    }
    onEmptyState {
    }
    onErrorState { _ ->
      // ScreeUiContent(updatedState, loginViewModel)
    }
  }
}

@Composable
fun ScreeUiContent(loginViewState: LoginViewState, loginViewModel: LoginViewModel) {
  Surface(modifier = Modifier.fillMaxSize()) {
    Column(
      modifier = Modifier.padding(16.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center,
    ) {
      CustomTextField(
        label = stringResource(id = R.string.username_label),
        value = loginViewState.userName,
        errorText = stringResource(id = loginViewState.userNameError.getErrorMessage()),
        showError = loginViewState.showUsernameError(),
      ) { userName ->
        loginViewModel.setInput(LoginInput.UserNameUpdated(userName))
      }
      Spacer(modifier = Modifier.height(16.dp))
      CustomTextField(
        label = stringResource(id = R.string.password_label),
        value = loginViewState.password,
        errorText = stringResource(id = loginViewState.passwordError.getErrorMessage()),
        showError = loginViewState.showPasswordError(),
      ) { password ->
        loginViewModel.setInput(LoginInput.PasswordUpdated(password))
      }
      Spacer(modifier = Modifier.height(16.dp))

      Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = { loginViewModel.login() },
      ) {
        Text(text = "Login")
      }
      Spacer(modifier = Modifier.height(16.dp))
      TextButton(onClick = { loginViewModel.setInput(LoginInput.RegisterButtonClicked) }) {
        Text(text = "Sign up Now!")
      }
    }
  }
}

@Composable
fun CustomTextField(
  label: String,
  value: String,
  showError: Boolean,
  errorText: String,
  visualTransformation: VisualTransformation = VisualTransformation.None,
  onChanged: (String) -> Unit,
) {
  OutlinedTextField(
    value = value,
    onValueChange = { onChanged(it) },
    label = { Text(text = label) },
    modifier = Modifier
      .fillMaxWidth()
      .padding(8.dp),
    isError = showError,
    visualTransformation = visualTransformation,
  )
  if (showError) {
    Text(
      text = errorText,
      color = Color.Red,
      modifier = Modifier.padding(all = 8.dp),
    )
  }
}
