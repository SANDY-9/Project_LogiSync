package com.core.domain.usecases.network

import com.core.domain.repository.GetHeartRateRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RequestChangeCriticalPoint @Inject constructor(
    private val heartRateRepository: GetHeartRateRepository
) {
    operator fun invoke(id: String, min: Int, max: Int) : Flow<Boolean> {
        return heartRateRepository.updateChangeCriticalPoint(id, min, max)
    }
}
