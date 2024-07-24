package com.core.domain.usecases.network

import com.core.domain.repository.GetArrestRepository
import com.core.model.Arrest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class GetArrestListUseCase @Inject constructor(
    private val getArrestRepository: GetArrestRepository,
) {
    operator fun invoke(): Flow<Map<LocalDate, List<Arrest>>> {
        return getArrestRepository.getArrestList().map {
            it.groupBy { it.time.toLocalDate() }.toSortedMap(compareByDescending { it })
        }
    }
}
