package com.minafarid.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideHeaderInterceptor(): Interceptor {
        return HeaderInterceptor()
    }

    // Http Logging Interceptor
    @Provides
    @Singleton
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
}
