package com.feature.arrest.details

import android.util.Log
import androidx.lifecycle.ViewModel
import com.core.model.Arrest
import com.feature.arrest.details.model.ArrestDetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ArrestDetailsViewModel @Inject constructor(
): ViewModel() {

    private val _stateFlow: MutableStateFlow<ArrestDetailsUiState> = MutableStateFlow(ArrestDetailsUiState())
    internal val stateFlow: StateFlow<ArrestDetailsUiState> = _stateFlow.asStateFlow()
    private val state get() = stateFlow.value

    internal fun getArrestDetails(arrest: Arrest) {
        Log.e("확인", "getArrestDetails: $arrest", )
    }


}
