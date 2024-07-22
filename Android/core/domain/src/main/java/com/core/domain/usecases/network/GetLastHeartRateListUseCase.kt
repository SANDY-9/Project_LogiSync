package com.core.domain.usecases.network

import com.core.domain.repository.GetHeartRateRepository
import com.core.model.HeartRate
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLastHeartRateListUseCase @Inject constructor(
    private val getHeartRateRepository: GetHeartRateRepository,
) {
    operator fun invoke(id: String): Flow<List<HeartRate>> {
        return getHeartRateRepository.getLastHeartRateList(id)
    }
}
