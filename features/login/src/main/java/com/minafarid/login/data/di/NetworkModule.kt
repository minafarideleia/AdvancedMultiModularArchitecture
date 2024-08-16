package com.minafarid.login.data.di

import com.google.gson.Gson
import com.minafarid.data.connectivity.NetworkMonitorInterface
import com.minafarid.data.constants.USER_ID_TAG
import com.minafarid.data.factory.ServiceFactory
import com.minafarid.data.source.NetworkDataSource
import com.minafarid.login.data.service.LoginService
import com.minafarid.login.data.source.LoginRemote
import com.minafarid.login.data.source.LoginRemoteImplementer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideLoginServiceFactory(serviceFactory: ServiceFactory): LoginService {
        return serviceFactory.create(LoginService::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkDataSource(
        loginService: LoginService,
        gson: Gson,
        networkMonitorInterface: NetworkMonitorInterface,
        @Named(USER_ID_TAG) userIdProvider: () -> String
    ): NetworkDataSource<LoginService> {
        return NetworkDataSource(loginService, gson, networkMonitorInterface, userIdProvider)
    }


    @Provides
    @Singleton
    fun provideLoginRemoteImplementer(networkDataSource: NetworkDataSource<LoginService>): LoginRemote {
        return LoginRemoteImplementer(networkDataSource)
    }

}