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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.minafarid.login.R
import com.minafarid.login.presentation.protocol.LoginInput
import com.minafarid.login.presentation.protocol.LoginOutput
import com.minafarid.login.presentation.protocol.LoginViewState
import com.minafarid.login.presentation.viewmodel.LoginViewModel

@Composable
fun LoginScreen(loginViewState: LoginViewState, loginViewModel: LoginViewModel) {
  var userNameValue by remember { mutableStateOf("") }
  var passwordValue by remember { mutableStateOf("") }

  // React to viewOutput events

  LaunchedEffect(loginViewModel) {
    loginViewModel.viewOutput.collect { output ->
      when (output) {
        is LoginOutput.NavigateToMain -> TODO()
        is LoginOutput.NavigateToRegister -> TODO()
        is LoginOutput.ShowError -> TODO()
      }
    }
  }

  Surface(modifier = Modifier.fillMaxSize()) {
    Column(
      modifier = Modifier.padding(16.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center,
    ) {
      CustomTextField(
        label = stringResource(id = R.string.username_label),
        value = userNameValue, // loginViewState.userName,
        errorText = stringResource(id = loginViewState.userNameError.getErrorMessage()),
        showError = loginViewState.showUsernameError(),
      ) { userName ->
        // loginViewModel.setInput(LoginInput.UserNameUpdated(userName))
        userNameValue = userName
      }
      Spacer(modifier = Modifier.height(16.dp))
      CustomTextField(
        label = stringResource(id = R.string.password_label),
        value = passwordValue, // loginViewState.password,
        errorText = stringResource(id = loginViewState.passwordError.getErrorMessage()),
        showError = loginViewState.showPasswordError(),
      ) { password ->
//                loginViewModel.setInput(LoginInput.PasswordUpdated(password))
        passwordValue = password
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
