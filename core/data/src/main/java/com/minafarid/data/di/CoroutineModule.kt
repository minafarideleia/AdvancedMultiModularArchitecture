package com.minafarid.data.di

import com.minafarid.data.constants.DISPATCHER_DEFAULT_TAG
import com.minafarid.data.constants.DISPATCHER_IO_TAG
import com.minafarid.data.constants.DISPATCHER_MAIN_TAG
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoroutineModule {

  @Provides
  @Singleton
  @Named(DISPATCHER_MAIN_TAG)
  fun provideMainCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.Main

  @Provides
  @Singleton
  @Named(DISPATCHER_DEFAULT_TAG)
  fun provideDispatcherCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.Default

  @Provides
  @Singleton
  @Named(DISPATCHER_IO_TAG)
  fun provideIoCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO
}
