package com.core.firebase.di

import android.content.Context
import com.core.firebase.BuildConfig
import com.core.firebase.UserDataSource
import com.core.firebase.UserDataSourceImpl
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object FirebaseModule {

    @Provides
    @Singleton
    fun provideDatabaseReference(@ApplicationContext context: Context): DatabaseReference {
        return FirebaseDatabase.getInstance().getReferenceFromUrl(BuildConfig.DATABASE_KEY)
    }

    @Provides
    @Singleton
    fun bindsUserDataSource(ref: DatabaseReference): UserDataSource {
        return UserDataSourceImpl(ref)
    }
}
