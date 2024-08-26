package com.minafarid.data.result

import com.minafarid.domain.model.ErrorMessage

interface UseCase<R> {

  suspend fun onSuccess(success: OutCome.Success<R>)

  suspend fun onEmpty()

  suspend fun onError(errorMessage: com.minafarid.domain.model.ErrorMessage)
}
