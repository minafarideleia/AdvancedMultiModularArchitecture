package com.minafarid.data.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import java.util.Locale

// headers
const val AUTHORIZATION_HEADER = "Authorization"
const val ACCEPT_HEADER = "Accept"
const val CONTENT_TYPE_HEADER = "Content-Type"
const val ACCEPT_LANGUAGE_HEADER = "Accept-Language"
const val CLIENT_ID_HEADER = "Client-Id"

// headers values
const val JSON = "application/json"
const val ARABIC_LANGUAGE = "ar-SA"
const val ENGLISH_LANGUAGE = "en-US"

class HeaderInterceptor(
  private val clientId: String,
  private val accessTokenProvider: () -> String?,
  private val languageProvider: () -> Locale,
) : Interceptor {
  override fun intercept(chain: Interceptor.Chain): Response {
    val request = chain.request()
    val builder = request.newBuilder()

    val language = if (languageProvider() == Locale.ENGLISH) {
      ENGLISH_LANGUAGE
    } else {
      ARABIC_LANGUAGE
    }

    builder.header(CLIENT_ID_HEADER, clientId)
      .header(ACCEPT_HEADER, JSON)
      .header(CONTENT_TYPE_HEADER, JSON)
      .header(ACCEPT_LANGUAGE_HEADER, language)

    accessTokenProvider()?.let {
      builder.header(AUTHORIZATION_HEADER, "Bearer $it")
    }

    return chain.proceed(builder.build())
  }
}
