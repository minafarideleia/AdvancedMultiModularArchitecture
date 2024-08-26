package com.minafarid.login.data.mapper

import com.minafarid.login.data.responses.UserResponse
import com.minafarid.login.domain.model.User

interface LoginMapper {
  suspend fun toDomain(userResponse: UserResponse): User
}
