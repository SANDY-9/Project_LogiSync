package com.core.data.repository.wearable

import com.core.domain.repository.WearableRepository
import com.sandy.bluetooth.MyWearableClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

private const val INTERVAL = 1200L

class WearableRepositoryImpl @Inject constructor(
    private val wearableClient: MyWearableClient,
) : WearableRepository {

    override fun getWearableConnectState(): Flow<Boolean> = flow {
        while (true) {
            val isConnected = wearableClient.isConnectWearable()
            emit(isConnected)
            delay(INTERVAL)
        }
    }.flowOn(Dispatchers.IO).distinctUntilChanged()
}
