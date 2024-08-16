package com.minafarid.login.data.source

import com.minafarid.data.result.OutCome
import com.minafarid.data.source.NetworkDataSource
import com.minafarid.login.data.requests.LoginRequestBody
import com.minafarid.login.data.service.LoginService
import com.minafarid.login.domain.User

class LoginRemoteImplementer(private val networkDataSource: NetworkDataSource<LoginService>) :
    LoginRemote {
    override suspend fun login(loginRequestBody: LoginRequestBody): OutCome<User> {
        TODO("Not yet implemented")
    }
}