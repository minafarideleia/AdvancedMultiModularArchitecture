package com.minafarid.login.data.source

import com.minafarid.data.result.OutCome
import com.minafarid.login.data.requests.LoginRequestBody
import com.minafarid.login.domain.User

class LoginRemoteImplementer : LoginRemote {
    override suspend fun login(loginRequestBody: LoginRequestBody): OutCome<User> {
        TODO("Not yet implemented")
    }
}