package com.enesselcuk.loginprotodatastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.enesselcuk.loginprotodatastore.LoginPreferences
import com.enesselcuk.loginprotodatastore.data.LoginSerializer
import com.enesselcuk.loginprotodatastore.util.DataStoreName.DATA_STORE_FILE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object BuildDataStoreModule {
    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext appContext: Context): DataStore<LoginPreferences> {
        return DataStoreFactory.create(
            serializer = LoginSerializer,
            produceFile = { appContext.dataStoreFile(DATA_STORE_FILE_NAME) },
            corruptionHandler = null
        )
    }
}