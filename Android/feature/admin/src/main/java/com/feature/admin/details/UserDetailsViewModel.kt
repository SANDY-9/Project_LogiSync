package com.feature.admin.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.domain.usecases.network.GetLastHeartRateListUseCase
import com.core.domain.usecases.network.GetLastMyArrestUseCase
import com.core.domain.usecases.network.RequestChangeCriticalPoint
import com.core.domain.usecases.prefs.GetAccountUseCase
import com.core.model.User
import com.feature.admin.details.model.UserDetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.timeout
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import kotlin.math.max
import kotlin.math.min
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val getLastMyArrestUseCase: GetLastMyArrestUseCase,
    private val getLastHeartRateListUseCase: GetLastHeartRateListUseCase,
    private val requestChangeCriticalPoint: RequestChangeCriticalPoint,
    private val getAccountUseCase: GetAccountUseCase,
): ViewModel() {

    private val _stateFlow: MutableStateFlow<UserDetailsUiState> = MutableStateFlow(UserDetailsUiState())
    internal val stateFlow: StateFlow<UserDetailsUiState> = _stateFlow.asStateFlow()
    private val state get() = stateFlow.value

    private var savedUser: User? = null

    internal fun getUserDetails(user: User) {
        if(savedUser == null) savedUser = user else return
        _stateFlow.value = state.copy(
            user = user,
            editMin = (user.minCriticalPoint ?: "").toString(),
            editMax = (user.maxCriticalPoint ?: "").toString(),
        )
        val id = user.id
        getAccountUseCase()
            .onStart {
                _stateFlow.value = state.copy(
                    loading = true,
                )
            }.filter {
            it != null
        }.flatMapLatest { account ->
            _stateFlow.update {
                val enableEdit = account?.id == user.id || user.duty == User.Duty.NORMAL
                it.copy(
                    account = account,
                    enableEdit = enableEdit,
                )
            }
            combine(
                getLastMyArrestUseCase(id),
                getLastHeartRateListUseCase(id)
            ) { lastMyArrest, lastHearRateList ->
                state.copy(
                    lastReportList = lastMyArrest,
                    lastHeartRateList = lastHearRateList,
                )
            }
        }.onEach {  state ->
            delay(500)
            _stateFlow.update { state.copy(loading = false) }
        }.catch {
            _stateFlow.value = state.copy(loading = false, networkError = it)
        }.timeout(10.seconds).launchIn(viewModelScope)
    }

    internal fun editMinPoint(input: String) {
        _stateFlow.value = state.copy(
            editMin = input.valid()
        )
    }

    internal fun editMaxPoint(input: String) {
        _stateFlow.value = state.copy(
            editMax = input.valid()
        )
    }

    private fun String.valid(): String {
        val digits = this.filter { it.isDigit() }
        return if (digits.length > 3) {
            digits.substring(0, 3)
        } else {
            digits
        }
    }

    internal fun openChangeBottomSheet() {
        _stateFlow.value = state.copy(
            changeBottomSheetVisible = !state.changeBottomSheetVisible
        )
    }

    internal fun requestChangeCriticalPoint() {
        val min = state.editMin.toIntOrNull() ?: return
        val max = state.editMax.toIntOrNull() ?: return
        val id = state.user?.id ?: return

        val editedMin = min(min, max)
        val editedMax = max(min, max)
        requestChangeCriticalPoint(id, editedMin, editedMax).onStart {
            _stateFlow.value = state.copy(
                loading = true,
                error = null,
            )
        }.onEach { result ->
            delay(700)
            if(result) {
                _stateFlow.value = state.copy(
                    loading = false,
                    user = state.user?.copy(
                        minCriticalPoint = editedMin,
                        maxCriticalPoint = editedMax,
                    ),
                    editMin = editedMin.toString(),
                    editMax = editedMax.toString(),
                    error = false,
                )
            } else {
                _stateFlow.value = state.copy(
                    loading = false,
                    error = true,
                )
            }
        }.catch {
            _stateFlow.value = state.copy(
                loading = false,
                error = true,
            )
        }.onCompletion {
            _stateFlow.value = state.copy(
                error = null,
            )
        }.timeout(10.seconds).launchIn(viewModelScope)
    }

}
