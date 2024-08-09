package com.minafarid.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Call
import okhttp3.Interceptor
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.Locale
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    @Named("Language")
    fun provideLanguage(): () -> Locale {
        return { Locale.ENGLISH } // todo get locale from user prefs later, move me to config module
    }

    @Provides
    @Singleton
    @Named("AccessToken")
    fun provideAccessToken(): () -> String? {
        return { "" } // todo get access token from user prefs later, move me to config module
    }

    @Provides
    @Singleton
    @Named("ClientId")
    fun provideClientId(): String {
        return "" // todo get client id from user prefs later, move me to config module
    }

    @Provides
    @Singleton
    @Named("HeaderInterceptor")
    fun provideHeaderInterceptor(
        @Named("ClientId") clientId: String,
        @Named("AccessToken") accessTokenProvider: () -> String?,
        @Named("Language") languageProvider: () -> Locale
    ): Interceptor {
        return HeaderInterceptor(
            clientId, accessTokenProvider, languageProvider
        )
    }

    // Http Logging Interceptor
    @Provides
    @Singleton
    @Named("OkHttpLoggingInterceptor")
    fun provideOkHttpLoggingInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        if (!BuildConfig.DEBUG) {
            interceptor.redactHeader(CLIENT_ID_HEADER) // redact any header that contains sensitive data.
            interceptor.redactHeader(AUTHORIZATION_HEADER) // redact any header that contains sensitive data.
        }

        return interceptor
    }

    // ok http factory
    @Provides
    @Singleton
    fun provideOkHttpCallFactory(interceptor: Interceptor): Call.Factory {
        return OkHttpClient.Builder().addInterceptor(interceptor)
            .retryOnConnectionFailure(true)
            .followRedirects(false)
            .followSslRedirects(false)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }

}
