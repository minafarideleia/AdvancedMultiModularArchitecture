package com.minafarid.login.domain.usecase

import com.minafarid.domain.result.OutCome
import com.minafarid.domain.usecase.AsyncUseCase
import com.minafarid.login.data.source.LoginRemote
import com.minafarid.login.domain.model.User
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRemote: LoginRemote) :
  AsyncUseCase<LoginUseCase.Input, User>() {

  override suspend fun run(input: Input): OutCome<User> {
    return loginRemote.login(username = input.username, password = input.password)
  }

  data class Input(val username: String, val password: String)
}
