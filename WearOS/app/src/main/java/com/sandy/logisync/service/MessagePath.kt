package com.sandy.logisync.service

internal enum class MessagePath(val path: String) {
    TEST("/test"),
    REQUEST_HEAT_RATE("/request_heart_rate"),
    LOGIN("/login"),
}
