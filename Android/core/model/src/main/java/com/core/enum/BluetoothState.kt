package com.core.enum

enum class BluetoothState {
    NONE, // 대기 상태
    PERMISSION_DENIED, // 권한이 거부된 상태
    DISABLED, // 기기가 블루투스를 지원하지 않는 상태
    OFF, // 블루투스가 꺼진 상태
    ON, // 블루투스가 켜져있는 상태
    SEARCHING,
    CONNECTED, // 블루투스가 현재 기기와 연결되어 있는 상태
    ERROR, // 에러가 발생했을 상황
}
