package com.sandy.logisync.wearable.message

enum class TranscriptionPath(val path: String) {
    SEND_LOGIN_RESPONSE("/login_transcription"),
    SEND_HEART_RATE("/heart_rate_transcription"),
    SEND_ARREST("/arrest_transcription"),
}
