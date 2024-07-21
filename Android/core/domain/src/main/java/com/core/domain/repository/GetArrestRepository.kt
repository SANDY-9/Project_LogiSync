package com.core.domain.repository

import com.core.model.Arrest
import kotlinx.coroutines.flow.Flow

interface GetArrestRepository {
    fun getLastMyArrestList(id: String): Flow<List<Arrest>>
    fun getMyArrestList(id: String): Flow<List<Arrest>>
    fun getArrestList(): Flow<List<Arrest>>
}
