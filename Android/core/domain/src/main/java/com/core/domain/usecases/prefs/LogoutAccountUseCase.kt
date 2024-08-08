package com.core.domain.usecases.prefs

import com.core.domain.repository.AuthPrefsRepository
import javax.inject.Inject

class LogoutAccountUseCase @Inject constructor(
    private val authPrefsRepository: AuthPrefsRepository,
) {
    suspend operator fun invoke() {
        return authPrefsRepository.logoutAccount()
    }
}
