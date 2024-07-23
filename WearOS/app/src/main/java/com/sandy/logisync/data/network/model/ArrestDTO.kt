package com.sandy.logisync.data.network.model

class ArrestDTO(
    val id: String,
    val name: String,
    val tel: String,
    val arrestType: String,
    val lat: Double,
    val lng: Double,
    val bpm: Int? = null,
)
