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
        CoroutineScope(Dispatchers.IO).launch {
            val data = p0.data.toString(Charsets.UTF_8)
            when (p0.path) {
                MessagePath.GET_INIT.path -> registerAccount(data)
                MessagePath.GET_LOGIN.path -> login(data)
                MessagePath.GET_REQUEST_HEAT_RATE.path -> collectHeartRate(data)
            }
        }
    }

    private suspend fun registerAccount(accountData: String) {
        wearableDataStoreRepository.registerAccount(accountData)
    }

    private suspend fun login(accountData: String) {
        val existAccount = wearableDataStoreRepository.getAccount().first()
        if (existAccount == null) {
            wearableDataStoreRepository.registerAccount(accountData)
        }
        val isLoginSuccess = existAccount == null || accountData.getId() == existAccount.id
        val loginResult = if (isLoginSuccess) MessageResponse.SUCCESS else MessageResponse.FAIL
        wearableTranscriptionRepository.resultLogin(loginResult)
    }

    private fun String.getId(): String {
        val jsonObject = JSONObject(this)
        return jsonObject.getString("id").trim()
    }

    // 앱을 킨다. 그리고 심박수를 측정한다
    private suspend fun collectHeartRate(id: String) {
        Log.i("[WEARABLE-MOBILE]", "collectHeartRate: $id", )
        val account = wearableDataStoreRepository.getAccount().first()
        account?.let {
            val idValid = it.id == id
            if(idValid) {
                turnOnApp()
                HeartRateMeasureWorker.enqueueWorker(this)
            }
        }
    }

    private fun turnOnApp() {
        CoroutineScope(Dispatchers.Main).launch {
            val intent = Intent(this@MyWearableListenerService, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                putExtra("HEART_RATE_COLLECT", true)
            }
            startActivity(intent)
        }
    }

}
