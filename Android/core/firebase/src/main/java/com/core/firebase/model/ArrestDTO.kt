package com.core.firebase.model

data class ArrestDTO(
    val id: String = "",
    val arrestType: String = "",
    val lng: Double = 0.0,
    val lat: Double = 0.0,
    val bpm: Int? = null
)
