package com.sandy.logisync.wearable.service

import android.content.Intent
import android.util.Log
import com.google.android.gms.wearable.MessageEvent
import com.google.android.gms.wearable.WearableListenerService
import com.sandy.logisync.data.datastore.WearableDataStoreRepository
import com.sandy.logisync.data.wearable.MessageResponse
import com.sandy.logisync.data.wearable.WearableTranscriptionRepository
import com.sandy.logisync.presentation.ui.MainActivity
import com.sandy.logisync.wearable.message.MessagePath
import com.sandy.logisync.workmanager.HeartRateMeasureWorker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@AndroidEntryPoint
class MyWearableListenerService : WearableListenerService() {

    @Inject
    lateinit var wearableDataStoreRepository: WearableDataStoreRepository
    @Inject
    lateinit var wearableTranscriptionRepository: WearableTranscriptionRepository

    // 노드에서 전송된 메시지가 타겟 노드에서 이 콜백을 트리거
    override fun onMessageReceived(p0: MessageEvent) {
        val data = p0.data.toString(Charsets.UTF_8)
        when (p0.path) {
            MessagePath.GET_INIT.path -> registerAccount(data)
            MessagePath.GET_LOGIN.path -> login(data)
            MessagePath.GET_REQUEST_HEAT_RATE.path -> collectHeartRate(data)
        }
    }

    private fun registerAccount(accountData: String) {
        CoroutineScope(Dispatchers.IO).launch {
            wearableDataStoreRepository.registerAccount(accountData)
        }
    }

    private fun login(accountData: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val existAccount = wearableDataStoreRepository.getAccount().first()
            if (existAccount == null) {
                wearableDataStoreRepository.registerAccount(accountData)
            }
            wearableTranscriptionRepository.resultLogin(
                if (existAccount == null || accountData.getId() == existAccount.id) MessageResponse.SUCCESS else MessageResponse.FAIL
            )
        }
    }

    private fun String.getId(): String {
        val jsonObject = JSONObject(this)
        return jsonObject.getString("id").trim()
    }

    // 앱을 킨다. 그리고 심박수를 측정한다
    private fun collectHeartRate(id: String) {
        Log.i("[WEARABLE-MOBILE]", "collectHeartRate: $id", )
        CoroutineScope(Dispatchers.IO).launch {
            val account = wearableDataStoreRepository.getAccount().first()
            account?.let {
                if(it.id == id) {
                    launch(Dispatchers.Main) { turnOnApp() }
                    HeartRateMeasureWorker.enqueueWorker(this@MyWearableListenerService)
                }
            }
        }
    }

    private fun turnOnApp() {
        val intent = Intent(this@MyWearableListenerService, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("HEART_RATE_COLLECT", true)
        }
        startActivity(intent)
    }

}
