package com.core.domain.usecases.wearable

import com.core.domain.repository.WearableRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWearableConnectStateUseCase @Inject constructor(
    private val wearableRepository: WearableRepository
) {
    operator fun invoke(): Flow<Boolean> {
        return wearableRepository.getWearableConnectState()
    }
}
