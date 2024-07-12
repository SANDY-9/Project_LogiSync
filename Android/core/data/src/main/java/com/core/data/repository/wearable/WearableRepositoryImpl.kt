package com.core.data.repository.wearable

import com.core.data.mapper.toDevice
import com.core.domain.repository.WearableRepository
import com.core.model.Account
import com.core.model.Device
import com.sandy.bluetooth.MyBluetoothManager
import com.sandy.bluetooth.MyWearableClient
import com.sandy.bluetooth.Transcription
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import org.json.JSONObject
import javax.inject.Inject

private const val INTERVAL = 1200L

class WearableRepositoryImpl @Inject constructor(
    private val wearableClient: MyWearableClient,
    private val bluetoothManager: MyBluetoothManager,
) : WearableRepository {

    // 테스트용
    override fun getWearableConnectState(): Flow<Device?> = flow {
        val node = wearableClient.getConnectWearable()
        if (node == null) {
            emit(null)
        }
        else {
            val alias = bluetoothManager.getDeviceAlias()
            emit(node.toDevice(alias))
        }
        /*while (true) {
            val node = wearableClient.getConnectWearable()
            if(node == null) {
                emit(null)
            }
            else {
                val alias = bluetoothManager.getDeviceAlias()
                emit(node.toDevice(alias))
            }
            delay(INTERVAL)
        }*/
    }.flowOn(Dispatchers.IO).distinctUntilChanged()

    override suspend fun sendLogin(account: Account) = withContext(Dispatchers.IO) {
        wearableClient.requestTranscription(
            data = account.toJson(),
            transcription = Transcription.LOGIN
        )
    }

    private fun Account.toJson(): String {
        val jsonObject = JSONObject()
        jsonObject.put("id", id)
        jsonObject.put("name", name)
        jsonObject.put("tel", tel)
        return jsonObject.toString()
    }

    override suspend fun requestCollectHeartRate(id: String) = withContext(Dispatchers.IO) {
        wearableClient.requestTranscription(
            data = id,
            transcription = Transcription.REQUEST_HEAT_RATE
        )
    }
}
