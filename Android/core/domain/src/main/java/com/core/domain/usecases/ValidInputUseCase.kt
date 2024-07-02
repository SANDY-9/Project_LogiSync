package com.core.domain.usecases

class ValidInputUseCase {
    operator fun invoke(input: String): Boolean {
        return input.trim().isNotBlank()
    }
}
