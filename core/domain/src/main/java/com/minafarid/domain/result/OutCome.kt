package com.minafarid.domain.result

import com.minafarid.domain.usecase.UseCase

sealed class OutCome<T> {
  abstract fun isSuccess(): Boolean

  open fun errorMessage(): com.minafarid.domain.model.ErrorMessage? = null

  abstract suspend fun accept(useCase: UseCase<T>)

  class Success<T>(val data: T) : OutCome<T>() {
    override fun isSuccess(): Boolean = true

    override suspend fun accept(useCase: UseCase<T>) {
      useCase.onSuccess(this)
    }
  }

  class Error<T>(private val errorMessage: com.minafarid.domain.model.ErrorMessage) : OutCome<T>() {
    override fun isSuccess(): Boolean = false

    override fun errorMessage(): com.minafarid.domain.model.ErrorMessage = errorMessage

    override suspend fun accept(useCase: UseCase<T>) {
      useCase.onError(errorMessage)
    }
  }

  class Empty<T>() : OutCome<T>() {
    override fun isSuccess(): Boolean = true

    override suspend fun accept(useCase: UseCase<T>) {
      useCase.onEmpty()
    }
  }

  companion object {
    fun <T> success(data: T) = Success(data)
    fun <T> error(errorMessage: com.minafarid.domain.model.ErrorMessage) = Error<T>(errorMessage)
    fun <T> empty() = Empty<T>()
  }
}
