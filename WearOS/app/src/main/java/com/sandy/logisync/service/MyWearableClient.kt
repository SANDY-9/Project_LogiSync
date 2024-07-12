package com.sandy.logisync.service

import android.util.Log
import com.google.android.gms.tasks.Tasks
import com.google.android.gms.wearable.CapabilityClient
import com.google.android.gms.wearable.CapabilityInfo
import com.google.android.gms.wearable.MessageClient
import com.google.android.gms.wearable.Node
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MyWearableClient @Inject constructor(
    private val capabilityClient: CapabilityClient,
    private val messageClient: MessageClient,
) {

    // CapabilityClient는 Wear OS 네트워크의 어느 노드가 어떤 맞춤 앱 기능을 지원하는지에 관한 정보를 제공
    fun setupConnectApp() {
        CoroutineScope(Dispatchers.IO).launch {
            val capabilityInfo = Tasks.await(
                capabilityClient.getCapability(
                    WEARABLE_CAPABILITY_NAME,
                    CapabilityClient.FILTER_REACHABLE
                )
            )
            updateTranscriptionCapability(capabilityInfo)
        }
    }

    private var transcriptionNodeId: String? = null
    private fun updateTranscriptionCapability(capabilityInfo: CapabilityInfo) {
        transcriptionNodeId = pickBestNodeId(capabilityInfo.nodes)
    }

    private fun pickBestNodeId(nodes: Set<Node>): String? {
        // Find a nearby node or pick one arbitrarily.
        return nodes.firstOrNull { it.isNearby }?.id ?: nodes.firstOrNull()?.id
    }

    // 메세지는 무조건 ByteArray형태
    fun requestTranscription(data: String, transcriptionPath: TranscriptionPath) {
        transcriptionNodeId?.also { nodeId ->
            messageClient.sendMessage(
                nodeId,
                transcriptionPath.path,
                data.toByteArray(Charsets.UTF_8)
            ).apply {
                addOnSuccessListener {
                    Log.e("확인", "requestTranscription: 앱->워치 메시지 전송 성공 $data")
                }
                addOnFailureListener {
                    Log.e("확인", "requestTranscription 실패: $it")
                }
            }
        }
    }

    companion object {
        private const val WEARABLE_CAPABILITY_NAME = "logi_sync_wear_app"
        private const val HEART_RATE_TRANSCRIPTION_MESSAGE_PATH = "/heart_rate_transcription"
    }
}
