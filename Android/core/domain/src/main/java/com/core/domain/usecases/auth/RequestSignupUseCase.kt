package com.core.domain.usecases.auth

import com.core.domain.repository.SignupRepository
import com.core.model.Account
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RequestSignupUseCase @Inject constructor(
    private val signupRepository: SignupRepository,
) {
    operator fun invoke(
        id: String,
        pwd: String,
        name: String,
        tel: String,
    ): Flow<Account> {
        return signupRepository.signup(id, pwd, name, tel)
    }
}
