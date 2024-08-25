package com.minafarid.data.interceptors

import com.minafarid.data.service.SessionService
import com.minafarid.data.source.DataSource.Companion.UNAUTHORISED
import com.minafarid.protodatastore.manager.session.SessionDataStoreInterface
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthenticationInterceptor @Inject constructor(
    private val sessionDataStoreInterface: SessionDataStoreInterface,
    private val sessionService: SessionService,
    private val coroutineDispatcher: CoroutineDispatcher
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val accessToken =
            runBlocking(coroutineDispatcher) { sessionDataStoreInterface.getAccessToken() }

        val authenticatedRequest =
            request.newBuilder().header(AUTHORIZATION_HEADER, "Bearer $accessToken").build()

        val response = chain.proceed(authenticatedRequest)

        if(response.code != UNAUTHORISED){
            // your access token is valid you can resume hitting APIs
            return  response
        }

        // Token is un authorized so try to refresh your access token and refresh token





    }
}