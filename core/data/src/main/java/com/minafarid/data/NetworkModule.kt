package com.minafarid.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
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


    // ok http factory
}