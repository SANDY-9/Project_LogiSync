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

    fun isConnectWearable(): Boolean {
        val capabilityInfo = Tasks.await(
            Wearable.getCapabilityClient(context)
                .getCapability(
                    WEARABLE_CAPABILITY_NAME,
                    CapabilityClient.FILTER_REACHABLE
                )
        )
        Log.e("확인", "getWearableCapabilityInfo: ${capabilityInfo.nodes}")
        return capabilityInfo.nodes.isNotEmpty()
    }

    companion object {
        private const val WEARABLE_CAPABILITY_NAME = "logi_sync_wear_app"
    }
}
