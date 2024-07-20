package com.core.domain.usecases.wearable

import com.core.domain.repository.AuthPrefsRepository
import com.core.domain.repository.WearableRepository
import com.core.model.Account
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class LoginWearableUseCase @Inject constructor(
    private val authPrefsRepository: AuthPrefsRepository,
    private val wearableRepository: WearableRepository
) {
    operator fun invoke(): Flow<Account?> {
        return authPrefsRepository.getAccount().onEach { account ->
            account?.let {
                wearableRepository.sendLogin(it)
            }
        }
    }
}
