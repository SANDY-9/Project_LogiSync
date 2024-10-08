package com.sandy.bluetooth

import android.util.Log
import com.google.android.gms.tasks.Tasks
import com.google.android.gms.wearable.CapabilityClient
import com.google.android.gms.wearable.CapabilityInfo
import com.google.android.gms.wearable.MessageClient
import com.google.android.gms.wearable.Node
import javax.inject.Inject

class MyWearableClient @Inject constructor(
    private val capabilityClient: CapabilityClient,
    private val messageClient: MessageClient,
) {

    // CapabilityClient는 Wear OS 네트워크의 어느 노드가 어떤 맞춤 앱 기능을 지원하는지에 관한 정보를 제공
    fun getConnectWearable(): Node? {
        val capabilityInfo = Tasks.await(
            capabilityClient.getCapability(
                WEARABLE_CAPABILITY_NAME,
                CapabilityClient.FILTER_REACHABLE
            )
        )
        updateTranscriptionCapability(capabilityInfo)
        return capabilityInfo.nodes.firstOrNull()
    }

    private var transcriptionNodeId: String? = null
    private fun updateTranscriptionCapability(capabilityInfo: CapabilityInfo) {
        transcriptionNodeId = pickBestNodeId(capabilityInfo.nodes)
    }

    private fun pickBestNodeId(nodes: Set<Node>): String? {
        return nodes.firstOrNull { it.isNearby }?.id ?: nodes.firstOrNull()?.id
    }

    fun requestTranscription(data: String, transcriptionPath: TranscriptionPath) {
        getConnectWearable()
        transcriptionNodeId?.also { nodeId ->
            messageClient.sendMessage(
                nodeId,
                transcriptionPath.path,
                data.toByteArray()
            ).apply {
                addOnSuccessListener {
                    Log.i("[WEARABLE_MOBILE]", "requestTranscription: 앱->워치 메시지 전송 성공 $data")
                }
            }
        }
    }

    companion object {
        private const val WEARABLE_CAPABILITY_NAME = "logi_sync_wear_app"
    }
}
