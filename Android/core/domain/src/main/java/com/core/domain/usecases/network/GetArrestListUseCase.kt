package com.core.domain.usecases.network

import com.core.domain.repository.GetArrestRepository
import com.core.model.Arrest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetArrestListUseCase @Inject constructor(
    private val getArrestRepository: GetArrestRepository,
) {
    operator fun invoke(): Flow<List<Arrest>> {
        return getArrestRepository.getArrestList()
    }
}
