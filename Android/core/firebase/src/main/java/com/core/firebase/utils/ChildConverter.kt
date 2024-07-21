package com.core.firebase.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

private val yearMonthFormatter = DateTimeFormatter.ofPattern("yyyyMM")
private val dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd")

internal fun LocalDate.child(): String {
    return yearMonthFormatter.format(this)
}

internal fun LocalDate.dateStr(): String {
    return dateFormatter.format(this)
}
