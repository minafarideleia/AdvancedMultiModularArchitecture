package com.minafarid.login.presentation.protocol

import com.minafarid.domain.model.ErrorMessage

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