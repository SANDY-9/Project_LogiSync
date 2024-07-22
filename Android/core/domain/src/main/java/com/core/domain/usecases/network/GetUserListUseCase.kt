package com.core.domain.usecases.network

import com.core.domain.repository.GetUserRepository
import com.core.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserListUseCase @Inject constructor(
    private val getUserRepository: GetUserRepository
) {
    operator fun invoke(): Flow<Map<User.Team, List<User>>?> {
        return getUserRepository.getUserList()
    }
}
