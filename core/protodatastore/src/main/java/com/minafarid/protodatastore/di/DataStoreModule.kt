package com.minafarid.protodatastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import com.minafarid.proto.Preferences
import com.minafarid.proto.Session
import com.minafarid.protodatastore.factory.preferencesDataStore
import com.minafarid.protodatastore.factory.sessionDataStore
import com.minafarid.protodatastore.manager.preferences.PreferencesDataStoreImplementer
import com.minafarid.protodatastore.manager.preferences.PreferencesDataStoreInterface
import com.minafarid.protodatastore.manager.session.SessionDataStoreImplementer
import com.minafarid.protodatastore.manager.session.SessionDataStoreInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideSessionDataStore(@ApplicationContext context: Context): DataStore<Session> {
        return context.sessionDataStore
    }

    @Provides
    @Singleton
    fun providePreferencesDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.preferencesDataStore
    }

    @Provides
    @Singleton
    fun provideSessionStoreManager(sessionDataStore: DataStore<Session>): SessionDataStoreInterface {
        return SessionDataStoreImplementer(sessionDataStore)
    }

    @Provides
    @Singleton
    fun provideSessionStoreManager(preferencesDataStore: DataStore<Preferences>): PreferencesDataStoreInterface {
        return PreferencesDataStoreImplementer(preferencesDataStore)
    }
}