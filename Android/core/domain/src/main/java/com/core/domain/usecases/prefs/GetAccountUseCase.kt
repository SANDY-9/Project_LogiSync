package com.core.domain.usecases.prefs

import com.core.domain.repository.AuthPrefsRepository
import com.core.model.Account
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAccountUseCase @Inject constructor(
    private val authPrefsRepository: AuthPrefsRepository,
) {
    operator fun invoke(): Flow<Account> {
        return authPrefsRepository.getAccount()
    }
}
