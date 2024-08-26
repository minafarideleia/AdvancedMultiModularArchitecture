package com.minafarid.data.mapper

import com.minafarid.data.response.ErrorResponse

// mapping errorResponse to ErrorMessage model
fun ErrorResponse.toDomain(code: Int): com.minafarid.domain.model.ErrorMessage {
  return com.minafarid.domain.model.ErrorMessage(
    code = code,
    message = errorMessage.orEmpty(),
    errorFieldList = errorFieldList ?: emptyList(),
  )
}
