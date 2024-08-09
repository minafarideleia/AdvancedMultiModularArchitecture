package com.minafarid.data

import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import java.security.cert.X509Certificate
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import javax.security.cert.CertificateException

class OkHttpClientProvider : OkHttpClientProviderInterface {
  private var dispatcher = Dispatcher()

  override fun getOkHttpClient(pin: String): OkHttpClient.Builder {
    try {
      // Create a trust manager that does not validate certificate chains
      val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {

        override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()

        @Throws(CertificateException::class)
        override fun checkClientTrusted(
          chain: Array<X509Certificate>,
          authType: String,
        ) {
        }

        @Throws(CertificateException::class)
        override fun checkServerTrusted(
          chain: Array<X509Certificate>,
          authType: String,
        ) {
        }
      })

      // Install the all-trusting trust manager
      val sslContext = SSLContext.getInstance("TLS")
      sslContext.init(null, trustAllCerts, java.security.SecureRandom())

      // Create an ssl socket factory with our all-trusting manager
      val sslSocketFactory = sslContext.socketFactory

      val builder = OkHttpClient.Builder()
      builder.dispatcher(dispatcher)
      builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
      builder.hostnameVerifier(HostnameVerifier { _, _ -> true })
      return builder
    } catch (e: Exception) {
      throw RuntimeException(e)
    }
  }

  override fun cancelAllRequests() {
    dispatcher.cancelAll()
  }
}
