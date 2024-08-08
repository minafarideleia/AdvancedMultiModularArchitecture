package com.minafarid.data

import okhttp3.Interceptor
import okhttp3.Response
import java.util.Locale

class HeaderInterceptor(
    private val clientId:String,
    private val accessTokenProvider: () -> String?,
    private val languageProvider: () -> Locale
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        TODO("Not yet implemented")
    }
}