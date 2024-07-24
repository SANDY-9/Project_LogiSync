package com.feature.admin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.domain.usecases.network.GetUserListUseCase
import com.feature.admin.model.AdminUiState
import com.feature.admin.utils.filter
import com.feature.admin.utils.filterArrest
import com.feature.admin.utils.filterHeart
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.timeout
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class AdminViewModel @Inject constructor(
    private val getUserListUseCase: GetUserListUseCase,
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<AdminUiState> = MutableStateFlow(AdminUiState())
    internal val stateFlow: StateFlow<AdminUiState> = _stateFlow.asStateFlow()
    private val state get() = stateFlow.value

    init {
        getUserListUseCase()
            .onStart {
                _stateFlow.value = state.copy(loading = true)
            }
            .onEach {
                it?.let { data ->
                    delay(500L)
                    _stateFlow.update {
                        state.copy(
                            loading = false,
                            userList = data,
                            searchUserList = data,
                            filteredUserList = data,
                        )
                    }
                }
            }.catch {
                Log.e("[USER_LIST]", "$it: ", )
                _stateFlow.value = state.copy(loading = false, error = it)
            }.timeout(10.seconds).launchIn(viewModelScope)
    }

    fun inputQuery(query: String) {
        _stateFlow.value = _stateFlow.value.copy(query = query)
    }

    fun clearQuery() {
        _stateFlow.value = _stateFlow.value.copy(query = "")
    }

    fun requestSearch() {
        if(state.query.isBlank()) return
        viewModelScope.launch {
            _stateFlow.value = state.copy(loading = true)
            delay(500L)
            val query = state.query.trim()
            val searchList = state.userList.filter(query)
            _stateFlow.value = state.copy(
                searchUserList = searchList,
                filteredUserList = searchList,
                allFilterSelected = true,
                dangerFilterSelected = false,
                heartFilterSelected = false,
                loading = false,
            )
        }
    }

    fun getAllMemberList() {
        if (state.allFilterSelected) return
        _stateFlow.value = state.copy(
            allFilterSelected = true,
            dangerFilterSelected = false,
            heartFilterSelected = false,
            filteredUserList = state.searchUserList,
        )
    }

    fun getHeartRateMemberList() {
        if (state.heartFilterSelected) return
        _stateFlow.value = state.copy(
            allFilterSelected = false,
            dangerFilterSelected = false,
            heartFilterSelected = true,
            filteredUserList = state.searchUserList.filterHeart()
        )
    }

    fun getDangerMemberList() {
        if (state.dangerFilterSelected) return
        _stateFlow.value = state.copy(
            allFilterSelected = false,
            dangerFilterSelected = true,
            heartFilterSelected = false,
            filteredUserList = state.searchUserList.filterArrest()
        )
    }

    fun refreshMemberList() {
        _stateFlow.value = state.copy(
            allFilterSelected = true,
            dangerFilterSelected = false,
            heartFilterSelected = false,
            filteredUserList = state.userList,
            searchUserList = state.userList,
        )
    }

}
