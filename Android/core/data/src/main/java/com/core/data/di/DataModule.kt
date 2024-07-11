package com.core.data.di

import com.core.data.repository.bluetooth.BluetoothRepositoryImpl
import com.core.data.repository.prefs.DevicePrefsRepositoryImpl
import com.core.data.repository.wearable.WearableRepositoryImpl
import com.core.domain.repository.BluetoothRepository
import com.core.domain.repository.DevicePrefsRepository
import com.core.domain.repository.WearableRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class DataModule {

    @Binds
    abstract fun bindsBluetoothRepository(
        impl: BluetoothRepositoryImpl
    ): BluetoothRepository

    @Binds
    abstract fun bindsWearableRepository(
        impl: WearableRepositoryImpl
    ): WearableRepository

    @Binds
    abstract fun bindsDevicePrefsRepository(
        impl: DevicePrefsRepositoryImpl
    ): DevicePrefsRepository
}
