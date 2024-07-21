package com.core.data.di

import com.core.data.repository.auth.LoginRepositoryImpl
import com.core.data.repository.auth.SignupRepositoryImpl
import com.core.data.repository.bluetooth.BluetoothRepositoryImpl
import com.core.data.repository.network.GetArrestRepositoryImpl
import com.core.data.repository.network.GetHeartRateRepositoryImpl
import com.core.data.repository.prefs.AuthPrefsRepositoryImpl
import com.core.data.repository.prefs.DevicePrefsRepositoryImpl
import com.core.data.repository.wearable.WearableRepositoryImpl
import com.core.domain.repository.AuthPrefsRepository
import com.core.domain.repository.BluetoothRepository
import com.core.domain.repository.DevicePrefsRepository
import com.core.domain.repository.GetArrestRepository
import com.core.domain.repository.GetHeartRateRepository
import com.core.domain.repository.LoginRepository
import com.core.domain.repository.SignupRepository
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

    @Binds
    abstract fun bindsLoginRepository(
        impl: LoginRepositoryImpl
    ): LoginRepository

    @Binds
    abstract fun bindsAuthPrefsRepository(
        impl: AuthPrefsRepositoryImpl
    ): AuthPrefsRepository

    @Binds
    abstract fun bindsSignupRepository(
        impl: SignupRepositoryImpl
    ): SignupRepository

    @Binds
    abstract fun bindsGetHeartRateRepository(
        impl: GetHeartRateRepositoryImpl
    ): GetHeartRateRepository

    @Binds
    abstract fun bindsGetArrestRepository(
        impl: GetArrestRepositoryImpl
    ): GetArrestRepository

}
