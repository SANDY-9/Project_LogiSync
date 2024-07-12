package com.core.domain.usecases.login

import com.core.domain.repository.AuthPrefsRepository
import com.core.domain.repository.LoginRepository
import com.core.model.Account
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class RequestLoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    private val authPrefsRepository: AuthPrefsRepository,
) {
    operator fun invoke(id: String, password: String): Flow<Account> {
        return loginRepository.login(id, password).onEach { account ->
            authPrefsRepository.updateAccount(account)
        }
    }
}
