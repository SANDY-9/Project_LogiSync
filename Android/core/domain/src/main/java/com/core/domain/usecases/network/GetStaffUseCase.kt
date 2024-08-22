package com.core.domain.usecases.network

import com.core.domain.repository.GetStaffRepository
import com.core.model.Staff
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStaffUseCase @Inject constructor(
    private val getStaffRepository: GetStaffRepository,
) {
    operator fun invoke(): Flow<List<Staff>> {
        return getStaffRepository.getStaffList()
    }
}
