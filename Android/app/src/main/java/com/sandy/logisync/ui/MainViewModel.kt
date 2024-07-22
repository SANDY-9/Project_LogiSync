package com.sandy.logisync.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.domain.usecases.prefs.GetAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getAccountUseCase: GetAccountUseCase
) : ViewModel() {

    val duty = getAccountUseCase().map {
        it?.duty
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = null
    )

}
