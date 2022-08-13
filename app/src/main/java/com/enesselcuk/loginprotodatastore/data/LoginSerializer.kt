package com.enesselcuk.loginprotodatastore.data

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.enesselcuk.loginprotodatastore.LoginPreferences
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object LoginSerializer : Serializer<LoginPreferences> {
    override val defaultValue: LoginPreferences = LoginPreferences.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): LoginPreferences {
        try {
            return LoginPreferences.parseFrom(input)
        } catch (ex: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", ex)
        }
    }

    override suspend fun writeTo(t: LoginPreferences, output: OutputStream) = t.writeTo(output)

}

