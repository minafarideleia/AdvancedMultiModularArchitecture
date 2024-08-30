package com.minafarid.login.presentation.protocol

import com.minafarid.domain.model.ErrorMessage
import com.minafarid.login.presentation.error.LoginError

sealed class LoginInput {
  data class UserNameUpdated(val username: String) : LoginInput()
  data class PasswordUpdated(val password: String) : LoginInput()
  data object LoginButtonClicked : LoginInput()
  data object RegisterButtonClicked : LoginInput()
}

sealed class LoginOutput {
  data object NavigateToMain : LoginOutput()
  data object NavigateToRegister : LoginOutput()
  data class ShowError(val errorMessage: ErrorMessage) : LoginOutput()
}

data class LoginViewState(
  val userName: String = "",
  val password: String = "",
  val isLoginButtonEnabled: Boolean = false,
  val userNameError: LoginError = LoginError.NoEntry,
  val passwordError: LoginError = LoginError.NoEntry,
) {
  fun showPasswordError() =
    passwordError != LoginError.NoError && passwordError != LoginError.NoEntry

  fun showUsernameError() =
    userNameError != LoginError.NoError && userNameError != LoginError.NoEntry
}
