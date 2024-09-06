package com.minafarid.login.data.mapper

import com.minafarid.domain.model.User
import com.minafarid.login.data.responses.UserResponse

interface LoginMapper {
  suspend fun toDomain(userResponse: UserResponse): User
}
