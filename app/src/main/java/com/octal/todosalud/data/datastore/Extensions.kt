package com.octal.todosalud.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import kotlinx.coroutines.flow.first
import timber.log.Timber
import java.lang.Exception

typealias AppDataStore = DataStore<AppDataStoreDto>

val Context.appDataStore by dataStore("app-data-store.json", AppDataStoreSerializer)

@SuppressWarnings("TooGenericExceptionCaught",)
suspend fun AppDataStore.getData(): AppDataStoreDto = try {
    this.data.first()
} catch (e: Exception) {
    Timber.e(e)
    AppDataStoreDto()
}
