package com.core.domain.usecases.signup

import com.core.data.repository.user.SignupRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckSignupUseCase @Inject constructor(
    private val signupRepository: SignupRepository
) {
    operator fun invoke(
        name: String,
        tel: String,
    ): Flow<Boolean> {
        return signupRepository.checkSignup(name, tel)
    }
}
