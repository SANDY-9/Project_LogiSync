package com.sandy.logisync.wearable.service

import android.util.Log
import com.google.android.gms.wearable.CapabilityInfo
import com.google.android.gms.wearable.MessageEvent
import com.google.android.gms.wearable.WearableListenerService
import com.sandy.logisync.data.datastore.WearableDataStoreRepository
import com.sandy.logisync.wearable.message.MessagePath
import com.sandy.logisync.wearable.message.MessageResponse
import com.sandy.logisync.wearable.message.MyWearableClient
import com.sandy.logisync.wearable.message.TranscriptionPath
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.json.JSONObject
import javax.inject.Inject

@AndroidEntryPoint
class MyWearableListenerService : WearableListenerService() {

    @Inject
    lateinit var wearableDataStoreRepository: WearableDataStoreRepository

    @Inject
    lateinit var myWearableClient: MyWearableClient

    // 노드에서 전송된 메시지가 타겟 노드에서 이 콜백을 트리거
    override fun onMessageReceived(p0: MessageEvent) {
        val data = p0.data.toString(Charsets.UTF_8)
        when (p0.path) {
            MessagePath.GET_LOGIN.path -> login(data)

            MessagePath.GET_REQUEST_HEAT_RATE.path -> {
                Log.e("확인", "onMessageReceived HEAT_RATE: ")
            }
        }
    }

    private fun login(accountData: String) {
        Log.e("확인", "login: $accountData")
        val scope = CoroutineScope(Dispatchers.IO)
        wearableDataStoreRepository.getAccount().onEach { existAccount ->
            if (existAccount == null) {
                wearableDataStoreRepository.registerAccount(accountData)
            }
            else {
                myWearableClient.requestTranscription(
                    if (accountData.getId() == existAccount.id) MessageResponse.SUCCESS else MessageResponse.FAIL,
                    TranscriptionPath.SEND_LOGIN_RESPONSE
                )
            }
        }.launchIn(scope)
    }

    private fun String.getId(): String {
        val jsonObject = JSONObject(this)
        return jsonObject.getString("id").trim()
    }

    // 앱의 인스턴스가 알리는 기능이 네트워크에서 사용 가능해지면 이벤트가 이 콜백을 트리거합니다.
    // 근처 노드를 찾고 있다면 이 콜백에서 제공된 노드의 isNearby() 메서드를 쿼리
    override fun onCapabilityChanged(p0: CapabilityInfo) {
        super.onCapabilityChanged(p0)
    }
}
