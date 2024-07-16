package com.feature.admin

import androidx.lifecycle.ViewModel
import com.feature.admin.model.AdminUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AdminViewModel @Inject constructor(

) : ViewModel() {

    private val _stateFlow: MutableStateFlow<AdminUiState> = MutableStateFlow(AdminUiState())
    internal val stateFlow: StateFlow<AdminUiState> = _stateFlow.asStateFlow()

    fun inputQuery(query: String) {
        _stateFlow.value = _stateFlow.value.copy(query = query)
    }

    fun clearQuery() {
        _stateFlow.value = _stateFlow.value.copy(query = "")
    }

    fun requestSearch() {
    }

    fun getAllMemberList() {
        val state = _stateFlow.value
        if (state.allFilterSelected) return
        _stateFlow.value = state.copy(
            allFilterSelected = true,
            attentionFilterSelected = false,
            dangerFilterSelected = false,
        )
    }

    fun getAttentionMemberList() {
        val state = _stateFlow.value
        if (state.attentionFilterSelected) return
        _stateFlow.value = state.copy(
            allFilterSelected = false,
            attentionFilterSelected = true,
            dangerFilterSelected = false,
        )
    }

    fun getDangerMemberList() {
        val state = _stateFlow.value
        if (state.dangerFilterSelected) return
        _stateFlow.value = state.copy(
            allFilterSelected = false,
            attentionFilterSelected = false,
            dangerFilterSelected = true,
        )
    }
}
