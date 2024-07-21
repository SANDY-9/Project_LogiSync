package com.sandy.logisync.data.network.model

class ArrestDTO(
    val id: String,
    val arrestType: String,
    val lat: Double,
    val lng: Double,
    val bpm: Int? = null,
)
