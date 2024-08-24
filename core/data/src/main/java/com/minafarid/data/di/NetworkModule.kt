package com.minafarid.data.di

import android.content.Context
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.minafarid.data.BuildConfig
import com.minafarid.data.OkHttpClientProvider
import com.minafarid.data.connectivity.NetworkMonitorImplementer
import com.minafarid.data.connectivity.NetworkMonitorInterface
import com.minafarid.data.constants.CHUCKER_INTERCEPTOR_TAG
import com.minafarid.data.constants.HEADER_INTERCEPTOR_TAG
import com.minafarid.data.constants.LOGGING_INTERCEPTOR_TAG
import com.minafarid.data.factory.ServiceFactory
import com.minafarid.data.okhttp.OkHttpClientProviderInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideNetworkMonitor(context: Context): NetworkMonitorInterface {
        return NetworkMonitorImplementer(context)
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
        @Named(CHUCKER_INTERCEPTOR_TAG) chuckerInterceptor: Interceptor,
        okHttpClientProvider: OkHttpClientProviderInterface,
    ): OkHttpClient {
        return okHttpClientProvider.getOkHttpClient(BuildConfig.PIN_CERTIFCATE)
            .addInterceptor(okHttpLoggingInterceptor)
            .addInterceptor(headerInterceptor)
            .addInterceptor(chuckerInterceptor)
            .retryOnConnectionFailure(true)
            .followRedirects(false)
            .followSslRedirects(false)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val builder = Retrofit.Builder()
            .baseUrl("")
            .client(okHttpClient)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideServiceFactory(retrofit: Retrofit): ServiceFactory {
        return ServiceFactory(retrofit)
    }
}
