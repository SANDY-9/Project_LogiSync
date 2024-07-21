package com.core.domain.usecases.network

import com.core.domain.repository.GetHeartRateRepository
import com.core.model.HeartRate
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDailyHeartRateListUseCase @Inject constructor(
    private val getHeartRateRepository: GetHeartRateRepository,
) {
    operator fun invoke(
        id: String,
        year: Int,
        month: Int,
        day: Int,
    ): Flow<List<HeartRate>> {
        return getHeartRateRepository.getHeartRateByDate(id, year, month, day)
    }
}
