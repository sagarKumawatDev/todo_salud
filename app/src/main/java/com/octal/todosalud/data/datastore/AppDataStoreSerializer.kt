package com.octal.todosalud.data.datastore

import androidx.datastore.core.Serializer
import kotlinx.serialization.json.Json
import timber.log.Timber
import java.io.InputStream
import java.io.OutputStream
import java.lang.Exception

@Suppress("BlockingMethodInNonBlockingContext")
internal object AppDataStoreSerializer : Serializer<AppDataStoreDto> {
    override val defaultValue: AppDataStoreDto
        get() = AppDataStoreDto()

    @SuppressWarnings("TooGenericExceptionCaught")
    override suspend fun readFrom(input: InputStream): AppDataStoreDto {
        return try {
            Json.decodeFromString(
                deserializer = AppDataStoreDto.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: Exception) {
            Timber.e(e)
            defaultValue
        }
    }

    override suspend fun writeTo(t: AppDataStoreDto, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = AppDataStoreDto.serializer(),
                value = t
            ).encodeToByteArray()
        )
    }
}
