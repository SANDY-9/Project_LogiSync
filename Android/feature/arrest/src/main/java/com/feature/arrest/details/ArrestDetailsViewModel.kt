package com.feature.arrest.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.domain.usecases.network.GetUserDetailsUseCase
import com.core.model.Arrest
import com.feature.arrest.details.model.ArrestDetailsUiState
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ArrestDetailsViewModel @Inject constructor(
    private val getUserDetailsUseCase: GetUserDetailsUseCase,
): ViewModel() {

    private val _stateFlow: MutableStateFlow<ArrestDetailsUiState> = MutableStateFlow(ArrestDetailsUiState())
    internal val stateFlow: StateFlow<ArrestDetailsUiState> = _stateFlow.asStateFlow()
    private val state get() = stateFlow.value

    internal fun getArrestDetails(arrest: Arrest) {
        _stateFlow.update {
            it.copy(
                arrest = arrest,
                arrestLocation = LatLng(arrest.lat, arrest.lng)
            )
        }
        getUserDetailsUseCase(arrest.id).onEach {
            it?.let {  user ->
                _stateFlow.update { state.copy(user = user) }
            }
        }.catch {
            Log.e("[GET_USER]", "$it", )
            _stateFlow.value = state.copy(error = it)
        }.launchIn(viewModelScope)
    }

    fun readyMap() {
        _stateFlow.update { it.copy(mapReady = true) }
    }


}
