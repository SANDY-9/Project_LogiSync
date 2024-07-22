package com.feature.arrest.details.model

import com.core.model.Arrest
import com.core.model.User

data class ArrestDetailsUiState(
    val arrest: Arrest? = null,
    val user: User? = null,
)
