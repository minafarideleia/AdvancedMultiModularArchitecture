package com.minafarid.login.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minafarid.login.presentation.protocol.LoginInput
import com.minafarid.login.presentation.protocol.LoginOutput
import com.minafarid.login.presentation.protocol.LoginViewState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private var loginViewState = LoginViewState()

    // output of viewmodel
    private val _viewOutPut: Channel<LoginOutput> = Channel()
    val viewOutput = _viewOutPut.receiveAsFlow()

    fun setInput(input: LoginInput) {
        when (input) {
            is LoginInput.LoginButtonClicked -> login()
            is LoginInput.PasswordUpdated -> TODO()
            is LoginInput.RegisterButtonClicked -> sendOutput { LoginOutput.NavigateToRegister }
            is LoginInput.UserNameUpdated -> TODO()
        }
    }

    private fun sendOutput(action: () -> LoginOutput) {
        viewModelScope.launch {
            _viewOutPut.send(action())
        }
    }

    fun login() {
    }
}
