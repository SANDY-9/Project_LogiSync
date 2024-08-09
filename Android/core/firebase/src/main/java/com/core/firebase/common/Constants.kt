package com.core.firebase.common

internal object Constants {
    const val PASSWORD = "pwd"
    const val NAME = "name"
    const val TEL = "tel"
    const val DUTY = "duty"
    const val ADMIN = "ADMIN"

    // main child
    const val USERS = "users"
    const val HEART_RATE = "heart_rate"
    const val TOKEN = "token"
    const val ARREST = "arrest"  // FCM Topic
    const val ID = "id"
    const val TEAM = "team"
    const val STAFF = "staff"

    // CRITICAL_POINT
    const val CRITICAL_POINT = "critical_point"
    const val MAX_CRITICAL_POINT = "max_heart_rate"
    const val MIN_CRITICAL_POINT = "min_heart_rate"
    const val MAX_HEART_RATE = 100
    const val MIN_HEART_RATE = 60


}
