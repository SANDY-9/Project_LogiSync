package com.sandy.logisync.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataStoreModule {

    private const val APP_PREFS_NAME = "app_prefs"

    // by preferencesDataStore: Context.appDataStore 이름을 가진 Datastore 인스턴스가 하나만 존재함을 보장하기 위해 위임
    private val Context.appDataStore: DataStore<Preferences> by preferencesDataStore(
        name = APP_PREFS_NAME
    )

    @Provides
    @Singleton
    fun provideAppPrefsDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.appDataStore
    }
}
