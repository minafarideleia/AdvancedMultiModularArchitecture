package com.minafarid.login.data.source

import com.minafarid.domain.result.OutCome
import com.minafarid.login.data.requests.LoginRequestBody
import com.minafarid.login.domain.model.User

interface LoginRemote {
    suspend fun login(username: String, password: String): OutCome<User>
}
