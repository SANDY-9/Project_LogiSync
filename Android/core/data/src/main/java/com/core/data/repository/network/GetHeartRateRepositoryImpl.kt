package com.core.data.repository.network

import com.core.data.mapper.toHeartRate
import com.core.data.mapper.toHeartRateList
import com.core.domain.repository.GetHeartRateRepository
import com.core.firebase.HeartRateClient
import com.core.model.HeartRate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class GetHeartRateRepositoryImpl @Inject constructor(
    private val heartRateClient: HeartRateClient,
) : GetHeartRateRepository {

    override fun getLastHeartRate(id: String): Flow<HeartRate?> = callbackFlow {
        heartRateClient.getLastHeartRate(
            id = id,
            onSuccess = {
                trySend(it?.toHeartRate())
            },
            onError = { e ->
                close(e)
            },
        )
        awaitClose()
    }.flowOn(Dispatchers.IO)

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

    override fun getHeartRateByPeriod(
        id: String,
        startDate: LocalDate,
        endDate: LocalDate
    ): Flow<List<HeartRate>> {
        return heartRateClient.getHeartRateByPeriod(id, startDate, endDate).map {
            it.toHeartRateList()
        }.flowOn(Dispatchers.IO)
    }

    override fun getLastHeartRateList(id: String): Flow<List<HeartRate>> = callbackFlow {
        heartRateClient.getLastHeartRateList(
            id = id,
            onSuccess = {
                trySend(it.toHeartRateList())
            },
            onError = { e ->
                close(e)
            },
        )
        awaitClose()
    }.flowOn(Dispatchers.IO)

    override fun updateChangeCriticalPoint(id: String, min: Int, max: Int): Flow<Boolean> = callbackFlow {
        heartRateClient.updateHeartRateCriticalPoint(
            id = id,
            min = min,
            max = max,
            onSuccess = { result ->
                trySend(result)
            },
            onError = {
                close(it)
            },
        )
        awaitClose()
    }.flowOn(Dispatchers.IO)
}
