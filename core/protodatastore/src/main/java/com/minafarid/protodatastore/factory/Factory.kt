package com.minafarid.protodatastore.factory

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.minafarid.proto.Preferences
import com.minafarid.proto.Session
import com.minafarid.protodatastore.serialzer.PreferencesSerializer
import com.minafarid.protodatastore.serialzer.SessionSerializer

val Context.sessionDataStore: DataStore<Session> by dataStore(
    fileName = "session.pb",
    serializer = SessionSerializer
)

val Context.preferencesDataStore: DataStore<Preferences> by dataStore(
    fileName = "preferences.pb",
    serializer = PreferencesSerializer
)