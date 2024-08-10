package com.minafarid.data.result

import com.minafarid.data.model.ErrorMessage

sealed class OutCome<T> {
    abstract fun isSuccess():Boolean

    open fun errorMessage(): ErrorMessage? = null

    abstract suspend fun accept(useCase: UseCase<T>)

    class Success<T>(val data:T):OutCome<T>(){
        override fun isSuccess(): Boolean  = true

        override suspend fun accept(useCase: UseCase<T>) {
            useCase.onSuccess(this)
        }
    }
}