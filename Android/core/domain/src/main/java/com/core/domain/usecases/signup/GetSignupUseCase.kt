package com.core.domain.usecases.signup

import com.core.data.repository.user.SignupRepository
import com.core.model.Account
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSignupUseCase @Inject constructor(
    private val signupRepository: SignupRepository
) {
    operator fun invoke(
        id: String,
        pwd: String,
        name: String,
        tel: String,
        duty: Account.Duty,
    ): Flow<Account> {
        return signupRepository.signup(id, pwd, name, tel, duty)
    }
}
