package com.core.domain.repository

import com.core.model.Staff
import kotlinx.coroutines.flow.Flow

interface GetStaffRepository {
    fun getStaffList(): Flow<List<Staff>>
}
