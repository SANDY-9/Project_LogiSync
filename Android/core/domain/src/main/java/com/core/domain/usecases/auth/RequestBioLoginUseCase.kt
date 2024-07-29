package com.core.domain.usecases.auth

import com.core.domain.repository.AuthPrefsRepository
import com.core.domain.repository.LoginRepository
import com.core.model.Account
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class RequestBioLoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    private val authPrefsRepository: AuthPrefsRepository,
) {
    operator fun invoke(id: String): Flow<Account?> {
        return loginRepository.bioLogin(id).onEach { account ->
            account?.let {
                authPrefsRepository.updateAccount(account)
            }
        }
    }
}
