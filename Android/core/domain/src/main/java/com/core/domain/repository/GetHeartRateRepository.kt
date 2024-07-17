package com.core.domain.repository

import com.core.model.HeartRate
import kotlinx.coroutines.flow.Flow

interface GetHeartRateRepository {

    fun getHeartRateByDate(
        id: String,
        year: Int,
        month: Int,
        day: Int,
    ): Flow<List<HeartRate>>
}
