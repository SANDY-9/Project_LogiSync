package com.sandy.logisync.data.di

import android.content.Context
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.sandy.logisync.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideDatabaseReference(): DatabaseReference {
        return Firebase.database.getReferenceFromUrl(BuildConfig.DATABASE_KEY)
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(
        @ApplicationContext context: Context,
    ): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .build()
    }
}
