package com.minafarid.protodatastore.serialzer

import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.minafarid.proto.Preferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.OutputStream

object PreferencesSerializer : Serializer<Preferences> {
    override val defaultValue: Preferences
        get() = Preferences.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): Preferences =
        withContext(Dispatchers.IO) {
            return@withContext try {
                Preferences.parseFrom(input)
            } catch (e: InvalidProtocolBufferException) {
                e.printStackTrace()
                defaultValue
            }
        }

    override suspend fun writeTo(t: Preferences, output: OutputStream) {
        withContext(Dispatchers.IO) {
            t.writeTo(output)
        }
    }
}