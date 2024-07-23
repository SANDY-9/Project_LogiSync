package com.sandy.logisync.data.network

import android.location.Location
import com.sandy.logisync.model.Arrest.ArrestType
import com.sandy.logisync.model.CriticalPoint
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

interface NetworkRepository {

    suspend fun getHeartRateCriticalPoint(id: String): Flow<CriticalPoint>

    suspend fun updateHeartRate(
        id: String,
        bpm: Int,
        time: LocalDateTime,
    ): Flow<Boolean>

    suspend fun updateNormalArrest(
        id: String,
        name: String,
        tel: String,
        arrestType: ArrestType,
        location: Location,
    ): Flow<Boolean>

    suspend fun updateHeartBeatArrest(
        id: String,
        name: String,
        tel: String,
        arrestType: ArrestType,
        location: Location,
        bpm: Int,
    ): Flow<Boolean>

    suspend fun notifyArrest(id: String): Flow<String?>


}
