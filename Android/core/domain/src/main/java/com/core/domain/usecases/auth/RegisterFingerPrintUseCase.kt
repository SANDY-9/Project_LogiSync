package com.core.domain.usecases.auth

import com.core.domain.repository.AuthPrefsRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class RegisterFingerPrintUseCase @Inject constructor(
    private val authPrefsRepository: AuthPrefsRepository,
) {
    suspend operator fun invoke() {
        val account = authPrefsRepository.getAccount().first()
        account?.let {
            authPrefsRepository.registerFingerPrint(it.id)
        }
    }
}
