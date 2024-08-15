package com.minafarid.data.source

import com.google.gson.Gson
import com.minafarid.data.connectivity.NetworkMonitorInterface
import com.minafarid.data.error.toDomain
import com.minafarid.data.response.ErrorResponse
import com.minafarid.data.result.OutCome
import okhttp3.Headers
import retrofit2.Response

class NetworkDataSource<SERVICE>(
    private val service: SERVICE,
    private val gson: Gson,
    private val networkMonitorInterface: NetworkMonitorInterface,
    private val userIdProvider: () -> String
) {

    suspend fun <R, T> performRequest(
        request: suspend SERVICE.(String) -> Response<R>,
        onSuccess: suspend (R, Headers) -> OutCome<T> = { _, _ -> OutCome.empty() },
        onRedirect: suspend (String, Int) -> OutCome<T> = { _, _ -> OutCome.empty() },
        onEmpty: suspend () -> OutCome<T> = { OutCome.empty() },
        onError: suspend (ErrorResponse, Int) -> OutCome<T> = { errorResponse, code ->
            OutCome.error(errorResponse.toDomain(code))
        },
    ): OutCome<T> {

    }


}