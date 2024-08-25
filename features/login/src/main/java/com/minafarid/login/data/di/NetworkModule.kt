package com.minafarid.login.data.di

import com.google.gson.Gson
import com.minafarid.data.constants.DISPATCHER_DEFAULT_TAG
import com.minafarid.data.constants.USER_ID_TAG
import com.minafarid.data.factory.ServiceFactory
import com.minafarid.data.source.NetworkDataSource
import com.minafarid.login.data.service.LoginService
import com.minafarid.login.data.source.LoginRemote
import com.minafarid.login.data.source.LoginRemoteImplementer
import com.minafarid.login.domain.mapper.LoginMapper
import com.minafarid.login.domain.mapper.LoginMapperImplementer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
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
    @Named(USER_ID_TAG) userIdProvider: () -> String,
  ): NetworkDataSource<LoginService> {
    return NetworkDataSource(loginService, gson, userIdProvider)
  }

  @Provides
  @Singleton
  fun provideLoginMapper(
    @Named(DISPATCHER_DEFAULT_TAG) coroutineDispatcher: CoroutineDispatcher,
  ): LoginMapper {
    return LoginMapperImplementer(coroutineDispatcher)
  }

  @Provides
  @Singleton
  fun provideLoginRemoteImplementer(
    networkDataSource: NetworkDataSource<LoginService>,
    loginMapper: LoginMapper,
  ): LoginRemote {
    return LoginRemoteImplementer(networkDataSource, loginMapper)
  }
}
