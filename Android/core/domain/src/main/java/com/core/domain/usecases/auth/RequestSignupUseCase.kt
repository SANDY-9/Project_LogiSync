package com.core.domain.usecases.auth

import com.core.domain.repository.AuthPrefsRepository
import com.core.domain.repository.SignupRepository
import com.core.model.Account
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class RequestSignupUseCase @Inject constructor(
    private val signupRepository: SignupRepository,
    private val authPrefsRepository: AuthPrefsRepository,
) {
    operator fun invoke(
        id: String,
        pwd: String,
        name: String,
        tel: String,
    ): Flow<Account> {
        return signupRepository.signup(id, pwd, name, tel).onEach {
            authPrefsRepository.updateAccount(it)
        }
    }
}
