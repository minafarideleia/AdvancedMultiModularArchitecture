package com.minafarid.data.di

import com.minafarid.data.constants.ACCESS_TOKEN_TAG
import com.minafarid.data.constants.CLIENT_ID_TAG
import com.minafarid.data.constants.LANGUAGE_TAG
import com.minafarid.data.constants.USER_ID_TAG
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.Locale
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ConfigModule {

  @Provides
  @Singleton
  @Named(USER_ID_TAG)
  fun provideUserId(): () -> String? {
    return { "" } // todo get user id from user prefs later
  }

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
}
