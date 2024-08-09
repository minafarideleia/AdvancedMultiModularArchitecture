package com.minafarid.data


import okhttp3.OkHttpClient

class OkHttpClientProvider : OkHttpClientProviderInterface{
    override fun getOkHttpClient(pin: String): OkHttpClient.Builder {
        TODO("Not yet implemented")
    }

    override fun cancelAllRequests() {
        TODO("Not yet implemented")
    }
}