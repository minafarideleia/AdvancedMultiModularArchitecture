package com.minafarid.login.data.source

import com.minafarid.domain.result.OutCome

interface LoginRemote {
  suspend fun login(username: String, password: String): OutCome<User>
}
