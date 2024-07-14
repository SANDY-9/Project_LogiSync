package com.sandy.logisync.wearable.message

enum class MessagePath(val path: String) {
    TEST("/test"),
    GET_REQUEST_HEAT_RATE("/request_heart_rate"),
    GET_LOGIN("/login"),
}