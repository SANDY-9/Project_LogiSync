package com.core.domain.usecases.auth

import com.core.domain.repository.SignupRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetIsExistId @Inject constructor(
    private val signupRepository: SignupRepository,
) {
    operator fun invoke(
        id: String,
    ): Flow<Boolean> {
        return signupRepository.checkId(id)
    }
}
