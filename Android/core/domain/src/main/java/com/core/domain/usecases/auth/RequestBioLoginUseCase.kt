package com.core.domain.usecases.auth

import com.core.domain.repository.LoginRepository
import com.core.model.Account
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RequestBioLoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
) {
    operator fun invoke(id: String): Flow<Account?> {
        return loginRepository.bioLogin(id)
    }
}
