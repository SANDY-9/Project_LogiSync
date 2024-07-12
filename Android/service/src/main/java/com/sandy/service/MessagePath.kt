package com.sandy.service

internal enum class MessagePath(val path: String) {
    GET_LOGIN_RESPONSE("/login_transcription"),
    GET_HEART_RATE("/heart_rate_transcription"),
    GET_ARREST("/arrest_transcription"),
}
