package com.core.domain.usecases.network

import com.core.domain.repository.GetArrestRepository
import com.core.model.Arrest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class GetMyArrestListUseCase @Inject constructor(
    private val getArrestRepository: GetArrestRepository,
) {
    operator fun invoke(id: String): Flow<Map<LocalDate, List<Arrest>>> {
        return getArrestRepository.getMyArrestList(id).map {
            it.toSortedMap(compareByDescending { it })
        }
    }
}
