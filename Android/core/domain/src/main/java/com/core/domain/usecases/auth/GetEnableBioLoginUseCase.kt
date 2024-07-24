package com.core.domain.usecases.auth

import com.core.domain.repository.AuthPrefsRepository
import javax.inject.Inject

class GetEnableBioLoginUseCase @Inject constructor(
    private val authPrefsRepository: AuthPrefsRepository,
){

    suspend operator fun invoke() : String? {
        return authPrefsRepository.getFingerPrint()
    }
}
