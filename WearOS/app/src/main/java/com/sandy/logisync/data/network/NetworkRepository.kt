package com.sandy.logisync.data.network

import android.location.Location
import com.sandy.logisync.model.Arrest.ArrestType
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

interface NetworkRepository {
    fun updateHeartRate(
        bpm: Int,
        time: LocalDateTime,
    ): Flow<Boolean>

    fun updateArrest(
        id: String,
        arrestType: ArrestType,
        location: Location,
        token: String,
    ): Flow<Boolean>

    suspend fun notifyArrest(
        id: String,
        token: String,
    ): Flow<String?>
}
