package com.core.firebase.di

import com.core.firebase.BuildConfig
import com.core.firebase.UserDataSource
import com.core.firebase.UserDataSourceImpl
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseDatabase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    @Provides
    @Singleton
    fun provideDatabaseReference(database: FirebaseDatabase): DatabaseReference {
        return database.getReferenceFromUrl(BuildConfig.DATABASE_KEY)
    }

    @Provides
    @Singleton
    fun bindsUserDataSource(ref: DatabaseReference): UserDataSource {
        return UserDataSourceImpl(ref)
    }
}
