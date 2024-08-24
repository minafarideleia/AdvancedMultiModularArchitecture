package com.minafarid.protodatastore.manager

import androidx.datastore.core.DataStore
import com.minafarid.proto.Session
import kotlinx.coroutines.flow.Flow

class SessionDataStoreImplementer(private val preferencesDataStore: DataStore<Session>) : SessionDataStoreInterface {
    override suspend fun setAccessToken(accessToken: String) {
        TODO("Not yet implemented")
    }

    override suspend fun setRefreshToken(refreshToken: String) {
        TODO("Not yet implemented")
    }

    override suspend fun setUserIdToken(userId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun setSession(accessToken: String, refreshToken: String, userId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getAccessToken(): String {
        TODO("Not yet implemented")
    }

    override fun getAccessTokenFlow(): Flow<String> {
        TODO("Not yet implemented")
    }

    override suspend fun getRefreshToken(): String {
        TODO("Not yet implemented")
    }

    override fun getRefreshTokenFlow(): Flow<String> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserId(): String {
        TODO("Not yet implemented")
    }

    override fun getUserIdFlow(): Flow<String> {
        TODO("Not yet implemented")
    }

    override suspend fun getSession(): Session {
        TODO("Not yet implemented")
    }

    override fun getSessionFlow(): Flow<Session> {
        TODO("Not yet implemented")
    }

}