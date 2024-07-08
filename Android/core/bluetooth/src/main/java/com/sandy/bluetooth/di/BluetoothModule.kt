package com.sandy.bluetooth.di

import android.bluetooth.BluetoothManager
import android.content.Context
import com.sandy.bluetooth.MyBluetoothManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BluetoothModule {

    @Singleton
    @Provides
    fun provideBluetoothManager(@ApplicationContext context: Context): BluetoothManager {
        return context.getSystemService(BluetoothManager::class.java)
    }

    @Singleton
    @Provides
    fun provideMyBluetoothManager(
        @ApplicationContext context: Context,
        bluetoothManager: BluetoothManager
    ): MyBluetoothManager {
        return MyBluetoothManager(context, bluetoothManager)
    }
}
