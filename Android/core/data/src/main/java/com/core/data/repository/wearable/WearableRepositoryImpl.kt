package com.core.data.repository.wearable

import android.util.Log
import com.core.data.mapper.toDevice
import com.core.domain.repository.WearableRepository
import com.core.model.Account
import com.core.model.Device
import com.sandy.bluetooth.MyBluetoothManager
import com.sandy.bluetooth.MyWearableClient
import com.sandy.bluetooth.TranscriptionPath
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import org.json.JSONObject
import javax.inject.Inject

private const val INTERVAL = 3000L

class WearableRepositoryImpl @Inject constructor(
    private val wearableClient: MyWearableClient,
    private val bluetoothManager: MyBluetoothManager,
) : WearableRepository {

    override fun getWearableConnectState(): Flow<Device?> = flow {
        while (true) {
            val node = wearableClient.getConnectWearable()
            Log.i("[WEARABLE_CONNECT_MONITORING]", "getWearableConnectState: $node", )
            if(node == null) {
                emit(null)
            }
            else {
                val alias = bluetoothManager.getDeviceAlias()
                emit(node.toDevice(alias))
            }
            delay(INTERVAL)
        }
    }.flowOn(Dispatchers.IO).distinctUntilChanged()

    override suspend fun sendLogin(account: Account) = withContext(Dispatchers.IO) {
        wearableClient.requestTranscription(
            data = account.toJson(),
            transcriptionPath = TranscriptionPath.SEND_LOGIN
        )
    }
    override suspend fun sendInit(account: Account) = withContext(Dispatchers.IO) {
        wearableClient.requestTranscription(
            data = account.toJson(),
            transcriptionPath = TranscriptionPath.SEND_INIT
        )
    }

    private fun Account.toJson(): String {
        val jsonObject = JSONObject()
        jsonObject.put("id", id)
        jsonObject.put("name", name)
        jsonObject.put("tel", tel)
        jsonObject.put("team", team)
        return jsonObject.toString()
    }

    override suspend fun requestCollectHeartRate(id: String) = withContext(Dispatchers.IO) {
        wearableClient.requestTranscription(
            data = id,
            transcriptionPath = TranscriptionPath.SEND_REQUEST_HEAT_RATE
        )
    }
}
