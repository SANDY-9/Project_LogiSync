package com.core.model

data class HeartRate(
    val rate: Int = 0,
    val date: String = "",
) {
    fun date(): String {
        return "오후 14:00"
    }
}
