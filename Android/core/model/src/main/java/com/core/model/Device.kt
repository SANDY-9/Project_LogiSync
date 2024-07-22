package com.core.model

data class Device(
    val name: String,
    val alias: String,
    val id: String,
    val isNearby: Boolean =false
)
