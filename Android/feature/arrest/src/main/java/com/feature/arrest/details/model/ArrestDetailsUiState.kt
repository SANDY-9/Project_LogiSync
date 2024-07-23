package com.feature.arrest.details.model

import com.core.model.Arrest
import com.core.model.User
import com.google.android.gms.maps.model.LatLng

data class ArrestDetailsUiState(
    val arrest: Arrest? = null,
    val user: User? = null,

    // map
    val arrestLocation: LatLng? = null,
    val mapReady: Boolean = false,
)
