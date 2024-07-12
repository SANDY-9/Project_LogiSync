package com.sandy.logisync.service

import android.util.Log
import com.google.android.gms.wearable.CapabilityInfo
import com.google.android.gms.wearable.MessageEvent
import com.google.android.gms.wearable.WearableListenerService

class MyWearableListenerService : WearableListenerService() {

    // 노드에서 전송된 메시지가 타겟 노드에서 이 콜백을 트리거
    override fun onMessageReceived(p0: MessageEvent) {
        val id = p0.data.toString(Charsets.UTF_8)
        Log.e("확인", "onMessageReceived: $id")
        when (p0.path) {
            MessagePath.LOGIN.path -> {
                Log.e("확인", "onMessageReceived LOGIN: ")
            }

            MessagePath.REQUEST_HEAT_RATE.path -> {
                Log.e("확인", "onMessageReceived HEAT_RATE: ")
            }
        }
    }

    // 앱의 인스턴스가 알리는 기능이 네트워크에서 사용 가능해지면 이벤트가 이 콜백을 트리거합니다.
    // 근처 노드를 찾고 있다면 이 콜백에서 제공된 노드의 isNearby() 메서드를 쿼리
    override fun onCapabilityChanged(p0: CapabilityInfo) {
        super.onCapabilityChanged(p0)
    }
}
