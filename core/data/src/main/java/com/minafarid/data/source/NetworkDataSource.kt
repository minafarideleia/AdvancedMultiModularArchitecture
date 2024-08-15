package com.minafarid.data.source

import com.google.gson.Gson
import com.minafarid.data.connectivity.NetworkMonitorInterface
import com.minafarid.data.error.getDefaultErrorResponse
import com.minafarid.data.error.toDomain
import com.minafarid.data.response.ErrorResponse
import com.minafarid.data.result.OutCome
import com.minafarid.data.source.DataSource.Companion.NO_INTERNET
import com.minafarid.data.source.DataSource.Companion.SSL_PINNING
import com.minafarid.data.source.DataSource.Companion.TIMEOUT
import com.minafarid.data.source.DataSource.Companion.UNKNOWN
import okhttp3.Headers
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException
import javax.net.ssl.SSLPeerUnverifiedException

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
        return if (networkMonitorInterface.hasConnectivity()) {
            try {

            } catch (e: Exception) {
                e.printStackTrace()
                val code = when (e) {
                    is SocketTimeoutException -> {
                        TIMEOUT
                    }

                    is UnknownHostException -> {
                        NO_INTERNET
                    }

                    is SSLPeerUnverifiedException, is SSLHandshakeException -> {
                        SSL_PINNING
                    }

                    else -> {
                        UNKNOWN
                    }
                }
                onError(getDefaultErrorResponse(), code)

            }
        } else {
            // NO INTERNET ERROR
            onError(getDefaultErrorResponse(), NO_INTERNET)
        }
    }
}