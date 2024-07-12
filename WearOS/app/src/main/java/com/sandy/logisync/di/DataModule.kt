package com.sandy.logisync.di

import com.sandy.logisync.data.datastore.WearableDataStoreRepository
import com.sandy.logisync.data.datastore.WearableDataStoreRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataModule {

    @Binds
    abstract fun bindsWearableDataStoreRepository(
        impl: WearableDataStoreRepositoryImpl
    ): WearableDataStoreRepository
}
