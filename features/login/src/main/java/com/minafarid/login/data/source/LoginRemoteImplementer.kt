package com.minafarid.login.data.source

import com.minafarid.data.mapper.toDomain
import com.minafarid.data.source.NetworkDataSource
import com.minafarid.domain.result.OutCome
import com.minafarid.login.data.mapper.LoginMapper
import com.minafarid.login.data.requests.LoginRequestBody
import com.minafarid.login.data.service.LoginService

class LoginRemoteImplementer(
  private val networkDataSource: NetworkDataSource<LoginService>,
  private val loginMapper: LoginMapper,
) :
  LoginRemote {
  override suspend fun login(username: String, password: String): OutCome<User> {
    return networkDataSource.performRequest(
      request = {
        login(
          LoginRequestBody(
            username = username,
            password = password,
          ),
        ).await()
      },
      onSuccess = { response, _ -> OutCome.success(loginMapper.toDomain(response)) },
      onError = { errorResponse, code -> OutCome.error(errorResponse.toDomain(code)) },
    )
  }
}
