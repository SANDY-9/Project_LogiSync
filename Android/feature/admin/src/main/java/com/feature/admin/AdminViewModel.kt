package com.feature.admin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.domain.usecases.network.GetUserListUseCase
import com.feature.admin.model.AdminUiState
import com.feature.admin.utils.filter
import com.feature.admin.utils.filterArrest
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
class AdminViewModel @Inject constructor(
    private val getUserListUseCase: GetUserListUseCase,
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<AdminUiState> = MutableStateFlow(AdminUiState())
    internal val stateFlow: StateFlow<AdminUiState> = _stateFlow.asStateFlow()
    private val state get() = stateFlow.value

    init {
        getUserListUseCase().onEach {
            it?.let { data ->
                _stateFlow.update {
                    state.copy(
                        userList = data,
                        searchUserList = data,
                        filteredUserList = data,
                    )
                }
            }
        }.catch {
            Log.e("[USER_LIST]", "$it: ", )
        }.launchIn(viewModelScope)
    }

    fun inputQuery(query: String) {
        _stateFlow.value = _stateFlow.value.copy(query = query)
    }

    fun clearQuery() {
        _stateFlow.value = _stateFlow.value.copy(query = "")
    }

    fun requestSearch() {
        if(state.query.isBlank()) return
        val query = state.query.trim()
        val searchList = state.userList.filter(query)
        _stateFlow.value = state.copy(
            searchUserList = searchList,
            filteredUserList = searchList,
            allFilterSelected = true,
            dangerFilterSelected = false,
        )
    }

    fun getAllMemberList() {
        val state = _stateFlow.value
        if (state.allFilterSelected) return
        _stateFlow.value = state.copy(
            allFilterSelected = true,
            dangerFilterSelected = false,
            filteredUserList = state.searchUserList,
        )
    }

    fun getDangerMemberList() {
        val state = _stateFlow.value
        if (state.dangerFilterSelected) return
        _stateFlow.value = state.copy(
            allFilterSelected = false,
            dangerFilterSelected = true,
            filteredUserList = state.searchUserList.filterArrest()
        )
    }

    fun refreshMemberList() {
        _stateFlow.value = state.copy(
            allFilterSelected = true,
            dangerFilterSelected = false,
            filteredUserList = state.userList,
            searchUserList = state.userList,
        )
    }
}
