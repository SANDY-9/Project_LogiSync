package com.sandy.logisync.wearable.message

enum class MessagePath(val path: String) {
    GET_INIT("/init"),
    GET_REQUEST_HEAT_RATE("/request_heart_rate"),
    GET_LOGIN("/login"),
}
