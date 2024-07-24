package com.core.domain.usecases.auth

import com.core.domain.repository.AuthPrefsRepository
import com.core.domain.repository.LoginRepository
import com.core.model.Account
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class RequestBioLogin @Inject constructor(
    private val authPrefsRepository: AuthPrefsRepository,
    private val loginRepository: LoginRepository,
) {
    suspend operator fun invoke(): Flow<Account?> {
        val id = authPrefsRepository.getFingerPrint() ?: return flowOf(null)
        return loginRepository.bioLogin(id)
    }
}
