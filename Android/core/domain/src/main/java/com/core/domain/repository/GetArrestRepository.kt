package com.core.domain.repository

import com.core.model.Arrest
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface GetArrestRepository {
    fun getLastMyArrestList(id: String): Flow<List<Arrest>>
    fun getMyArrestList(id: String): Flow<Map<LocalDate, List<Arrest>>>
    fun getArrestList(): Flow<List<Arrest>>
}
