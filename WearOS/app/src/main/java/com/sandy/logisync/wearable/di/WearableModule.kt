package com.sandy.logisync.wearable.di

import android.content.Context
import com.google.android.gms.wearable.CapabilityClient
import com.google.android.gms.wearable.MessageClient
import com.google.android.gms.wearable.Wearable
import com.sandy.logisync.wearable.MyWearableClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WearableModule {

    @Singleton
    @Provides
    fun provideCapabilityClient(@ApplicationContext context: Context): CapabilityClient {
        return Wearable.getCapabilityClient(context)
    }

    @Singleton
    @Provides
    fun provideMessageClient(@ApplicationContext context: Context): MessageClient {
        return Wearable.getMessageClient(context)
    }

    @Singleton
    @Provides
    fun provideMyWearableClient(
        capabilityClient: CapabilityClient,
        messageClient: MessageClient
    ): MyWearableClient {
        return MyWearableClient(capabilityClient, messageClient)
    }
}
