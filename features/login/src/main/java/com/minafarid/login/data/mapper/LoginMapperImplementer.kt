package com.minafarid.login.data.mapper

import com.minafarid.login.data.responses.UserResponse
import com.minafarid.login.domain.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class LoginMapperImplementer(private val defaultDispatcher: CoroutineDispatcher) : LoginMapper {
  override suspend fun toDomain(userResponse: UserResponse): User {
    return withContext(defaultDispatcher) {
      User(
        id = userResponse.id.orEmpty(),
        fullName = userResponse.fullName.orEmpty(),
        email = userResponse.email.orEmpty(),
        photo = userResponse.photo.orEmpty(),
      )
    }
  }
}
