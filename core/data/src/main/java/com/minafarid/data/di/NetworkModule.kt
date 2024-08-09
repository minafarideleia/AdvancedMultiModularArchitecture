package com.minafarid.data.di

import com.minafarid.data.interceptors.AUTHORIZATION_HEADER
import com.minafarid.data.BuildConfig
import com.minafarid.data.interceptors.CLIENT_ID_HEADER
import com.minafarid.data.interceptors.HeaderInterceptor
import com.minafarid.data.OkHttpClientProvider
import com.minafarid.data.constants.ACCESS_TOKEN_TAG
import com.minafarid.data.constants.CLIENT_ID_TAG
import com.minafarid.data.constants.HEADER_INTERCEPTOR_TAG
import com.minafarid.data.constants.LANGUAGE_TAG
import com.minafarid.data.constants.LOGGING_INTERCEPTOR_TAG
import com.minafarid.data.okhttp.OkHttpClientProviderInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Call
import okhttp3.Interceptor
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
    @Named(LANGUAGE_TAG)
    fun provideLanguage(): () -> Locale {
        return { Locale.ENGLISH } // todo get locale from user prefs later, move me to config module
    }

    @Provides
    @Singleton
    @Named(ACCESS_TOKEN_TAG)
    fun provideAccessToken(): () -> String? {
        return { "" } // todo get access token from user prefs later, move me to config module
    }

    @Provides
    @Singleton
    @Named(CLIENT_ID_TAG)
    fun provideClientId(): String {
        return "" // todo get client id from user prefs later, move me to config module
    }

    @Provides
    @Singleton
    @Named(HEADER_INTERCEPTOR_TAG)
    fun provideHeaderInterceptor(
        @Named(CLIENT_ID_TAG) clientId: String,
        @Named(ACCESS_TOKEN_TAG) accessTokenProvider: () -> String?,
        @Named(LANGUAGE_TAG) languageProvider: () -> Locale,
    ): Interceptor {
        return HeaderInterceptor(
            clientId,
            accessTokenProvider,
            languageProvider,
        )
    }

    // Http Logging Interceptor
    @Provides
    @Singleton
    @Named(LOGGING_INTERCEPTOR_TAG)
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

    @Provides
    @Singleton
    fun provideOkHttpClientProvider(): OkHttpClientProviderInterface {
        return OkHttpClientProvider()
    }

    // ok http factory
    @Provides
    @Singleton
    fun provideOkHttpCallFactory(
        @Named(LOGGING_INTERCEPTOR_TAG) okHttpLoggingInterceptor: Interceptor,
        @Named(HEADER_INTERCEPTOR_TAG) headerInterceptor: Interceptor,
        okHttpClientProvider: OkHttpClientProviderInterface
    ): Call.Factory {
        return okHttpClientProvider.getOkHttpClient(BuildConfig.PIN_CERTIFCATE)
            .addInterceptor(okHttpLoggingInterceptor)
            .addInterceptor(headerInterceptor)
            .retryOnConnectionFailure(true)
            .followRedirects(false)
            .followSslRedirects(false)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }
}
