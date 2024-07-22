package com.core.domain.repository

import com.core.model.HeartRate
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface GetHeartRateRepository {

    fun getLastHeartRate(id: String): Flow<HeartRate?>

    fun getHeartRateByDate(
        id: String,
        year: Int,
        month: Int,
        day: Int,
    ): Flow<List<HeartRate>>

    fun getHeartRateByPeriod(
        id: String,
        startDate: LocalDate,
        endDate: LocalDate,
    ): Flow<List<HeartRate>>

    fun getLastHeartRateList(id: String) : Flow<List<HeartRate>>

}
