package com.minafarid.data

import okhttp3.CertificatePinner
import okhttp3.Dispatcher
import okhttp3.OkHttpClient

class OkHttpClientProvider : OkHttpClientProviderInterface {
    private var dispatcher = Dispatcher()

    override fun getOkHttpClient(pin: String): OkHttpClient.Builder {
        val certificatePinner = CertificatePinner.Builder().add("*.yourdomain.com", pin).build()

        val builder = OkHttpClient.Builder()
        builder.dispatcher(dispatcher)
        builder.certificatePinner(certificatePinner)
        return builder
    }

    override fun cancelAllRequests() {
        dispatcher.cancelAll()
    }
}