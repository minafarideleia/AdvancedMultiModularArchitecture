package com.minafarid.domain.usecase

import com.minafarid.domain.result.OutCome

interface UseCase<R> {

  suspend fun onSuccess(success: OutCome.Success<R>)

  suspend fun onEmpty()

  suspend fun onError(errorMessage: com.minafarid.domain.model.ErrorMessage)
}
