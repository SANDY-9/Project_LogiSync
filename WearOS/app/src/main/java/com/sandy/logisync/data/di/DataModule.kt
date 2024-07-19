package com.sandy.logisync.data.di

import com.sandy.logisync.data.datastore.WearableDataStoreRepository
import com.sandy.logisync.data.datastore.WearableDataStoreRepositoryImpl
import com.sandy.logisync.data.health.HealthMeasureRepository
import com.sandy.logisync.data.health.HealthMeasureRepositoryImpl
import com.sandy.logisync.data.location.LocationRepository
import com.sandy.logisync.data.location.LocationRepositoryImpl
import com.sandy.logisync.data.network.NetworkRepository
import com.sandy.logisync.data.network.NetworkRepositoryImpl
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

    @Binds
    abstract fun bindsHealthMeasureRepository(
        impl: HealthMeasureRepositoryImpl
    ): HealthMeasureRepository

    @Binds
    abstract fun bindsNetworkRepository(
        impl: NetworkRepositoryImpl
    ): NetworkRepository

    @Binds
    abstract fun bindsLocationRepository(
        impl: LocationRepositoryImpl
    ): LocationRepository
}
