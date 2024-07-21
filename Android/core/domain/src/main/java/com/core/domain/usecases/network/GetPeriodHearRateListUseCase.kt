package com.core.domain.usecases.network

import com.core.domain.repository.GetHeartRateRepository
import com.core.model.HeartRate
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class GetPeriodHearRateListUseCase @Inject constructor(
    private val getHeartRateRepository: GetHeartRateRepository,
) {
    operator fun invoke(
        id: String,
        startDate: LocalDate,
        endDate: LocalDate,
    ): Flow<List<HeartRate>> {
        return getHeartRateRepository.getHeartRateByPeriod(id, startDate, endDate)
    }

}
