package com.core.domain.usecases.network

import com.core.domain.repository.GetArrestRepository
import com.core.model.Arrest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLastMyArrestUseCase @Inject constructor(
    private val getArrestRepository: GetArrestRepository,
) {
    operator fun invoke(id: String) : Flow<List<Arrest>> {
        return getArrestRepository.getLastMyArrestList(id)
    }
}
