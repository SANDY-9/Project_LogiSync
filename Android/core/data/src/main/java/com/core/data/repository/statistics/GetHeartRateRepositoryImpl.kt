package com.core.data.repository.statistics

import com.core.data.mapper.toHeartRateList
import com.core.domain.repository.GetHeartRateRepository
import com.core.firebase.HeartRateClient
import com.core.model.HeartRate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetHeartRateRepositoryImpl @Inject constructor(
    private val heartRateClient: HeartRateClient,
) : GetHeartRateRepository {
    override fun getHeartRateByDate(
        id: String,
        year: Int,
        month: Int,
        day: Int,
    ): Flow<List<HeartRate>> {
        val yearMonth = "$year${String.format("%02d", month)}"
        return heartRateClient.getHeartRateByDate(id, yearMonth, day).map {
            it.toHeartRateList()
        }.flowOn(Dispatchers.IO)
    }
}
