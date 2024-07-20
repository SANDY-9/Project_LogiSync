package com.core.domain.usecases.network

import com.core.domain.repository.GetHeartRateRepository
import com.core.model.HeartRate
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLastHeartRateUseCase @Inject constructor(
    private val getHeartRateRepository: GetHeartRateRepository,
) {
    operator fun invoke(id: String): Flow<HeartRate?> {
        return getHeartRateRepository.getLastHeartRate(id)
    }
}
