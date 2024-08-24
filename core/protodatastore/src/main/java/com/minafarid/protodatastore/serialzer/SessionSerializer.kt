package com.minafarid.protodatastore.serialzer

import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.minafarid.proto.Session
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.OutputStream

object SessionSerializer : Serializer<Session> {
  override val defaultValue: Session
    get() = Session.getDefaultInstance()

  override suspend fun readFrom(input: InputStream): Session =
    withContext(Dispatchers.IO) {
      return@withContext try {
        Session.parseFrom(input)
      } catch (e: InvalidProtocolBufferException) {
        e.printStackTrace()
        defaultValue
      }
    }

  override suspend fun writeTo(t: Session, output: OutputStream) {
    withContext(Dispatchers.IO) {
      t.writeTo(output)
    }
  }
}
