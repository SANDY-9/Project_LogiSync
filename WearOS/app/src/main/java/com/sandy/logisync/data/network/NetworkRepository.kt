package com.sandy.logisync.data.network

import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

interface NetworkRepository {
    fun updateHeartRate(
        bpm: Int,
        time: LocalDateTime,
    ): Flow<Boolean>
}
