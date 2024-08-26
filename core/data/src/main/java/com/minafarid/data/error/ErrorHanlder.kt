package com.minafarid.data.error

import com.google.gson.Gson
import com.minafarid.data.response.ErrorResponse

// create default error response

fun getDefaultErrorResponse() = ErrorResponse("", "", emptyList())

// getting error response from error body "string"

fun getErrorResponse(gson: Gson, errorBodyString: String): ErrorResponse =
  try {
    gson.fromJson(errorBodyString, ErrorResponse::class.java)
  } catch (e: Exception) {
    getDefaultErrorResponse()
  }
