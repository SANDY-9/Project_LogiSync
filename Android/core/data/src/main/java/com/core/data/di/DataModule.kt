package com.core.data.di

import com.core.data.repository.user.SignupRepository
import com.core.data.repository.user.impl.SignupRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class DataModule {

    @Binds
    abstract fun bindsSignupRepository(
        impl: SignupRepositoryImpl,
    ): SignupRepository
}
