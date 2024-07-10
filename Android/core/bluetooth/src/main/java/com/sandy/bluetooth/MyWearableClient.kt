package com.sandy.bluetooth

import android.content.Context
import android.util.Log
import com.google.android.gms.tasks.Tasks
import com.google.android.gms.wearable.CapabilityClient
import com.google.android.gms.wearable.Wearable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MyWearableClient @Inject constructor(
    private val context: Context,
) {

    // 서비스 연동이 가능한지 flow 방출
    fun getWearableCapabilityStateFlow(interval: Long = 1200L): Flow<Boolean> = flow {
        while (true) {
            val capabilityInfo = Tasks.await(
                Wearable.getCapabilityClient(context)
                    .getCapability(
                        WEARABLE_CAPABILITY_NAME,
                        CapabilityClient.FILTER_REACHABLE
                    )
            )
            // capabilityInfo has the reachable nodes with the transcription capability
            Log.e("확인", "getWearableCapabilityInfo: ${capabilityInfo.nodes}")
            emit(capabilityInfo.nodes.isNotEmpty())
            delay(interval)
        }
    }.flowOn(Dispatchers.IO).distinctUntilChanged()

    fun installLogiSyncWearApp() {
        val nodesTask = Wearable.getNodeClient(context).connectedNodes
        nodesTask.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val node = task.result
            }
        }
    }

    companion object {
        private const val WEARABLE_CAPABILITY_NAME = "logi_sync_wear_app"
    }
}
