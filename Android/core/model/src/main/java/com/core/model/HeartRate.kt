package com.core.model

import com.core.utils.DateUtil

data class HeartRate(
    val bpm: Int = 0,
    val date: String = "",
) {
    fun time(): String {
        return DateUtil.convertTime(date)
    }
}
