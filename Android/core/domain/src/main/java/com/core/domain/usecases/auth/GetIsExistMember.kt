package com.core.domain.usecases.auth

import com.core.domain.repository.SignupRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetIsExistMember @Inject constructor(
    private val signupRepository: SignupRepository,
) {
    operator fun invoke(
        tel: String,
    ): Flow<Boolean> {
        return signupRepository.checkTel(tel)
    }
}
