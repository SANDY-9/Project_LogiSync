package com.core.domain.usecases.signup

import com.core.data.repository.user.SignupRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckIdUseCase @Inject constructor(
    private val signupRepository: SignupRepository
) {
    operator fun invoke(
        id: String,
    ): Flow<Boolean> {
        return signupRepository.checkId(id)
    }
}
