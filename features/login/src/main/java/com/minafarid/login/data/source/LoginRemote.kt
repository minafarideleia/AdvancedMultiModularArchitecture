package com.minafarid.login.data.source

import com.minafarid.data.result.OutCome
import com.minafarid.login.data.requests.LoginRequestBody
import com.minafarid.login.domain.model.User

interface LoginRemote {
    suspend fun login(loginRequestBody: LoginRequestBody): OutCome<User>
}