package com.sandy.logisync.presentation.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.sandy.logisync.data.datastore.WearableDataStoreRepository
import com.sandy.logisync.domain.RequestNormalArrestUseCase
import com.sandy.logisync.model.Account
import com.sandy.logisync.workmanager.HeartRateMeasureWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val wearableDataStoreRepository: WearableDataStoreRepository,
    private val requestNormalArrestUseCase: RequestNormalArrestUseCase,
    application: Application,
) : AndroidViewModel(application) {

    private val _initialPairedMobile = MutableStateFlow(false)
    val initialPairedMobile = _initialPairedMobile.asStateFlow()

    private val _heartRateCollectLoading = MutableStateFlow(false)
    val heartRateCollectLoading = _heartRateCollectLoading.asStateFlow()

    val measuredHeartRate = wearableDataStoreRepository.getLastHeartRate().stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        null
    ).onEach {
        _heartRateCollectLoading.value = false
    }

    private val _isGrantedPermission = MutableStateFlow(false)
    val isGrantedPermission = _isGrantedPermission.asStateFlow()

    private val _account = MutableStateFlow<Account?>(null)
    val account = _account.asStateFlow()

    init {
        wearableDataStoreRepository.getAccount().onEach {
            _initialPairedMobile.value = it != null
            it?.let {
                _account.value = it
            }
        }.launchIn(viewModelScope)
    }

    fun getRequestCollect(collecting: Boolean) {
        if(collecting) {
            viewModelScope.launch {
                delay(500)
                _heartRateCollectLoading.value = true
            }
        }
    }

    fun updateGrantedPermission(isGranted: Boolean) {
        _isGrantedPermission.value = isGranted
    }

    fun collectHeartRate() {
        _heartRateCollectLoading.value = true
        val context = getApplication<Application>().applicationContext
        HeartRateMeasureWorker.enqueueWorker(context)
    }

    fun arrest() {
        viewModelScope.launch {
            val account = wearableDataStoreRepository.getAccount().first()
            account?.let {
                requestNormalArrestUseCase(it.id, it.name, it.tel).catch {
                    Log.e("[REQUEST_ARREST]", "$it : ${it.message}")
                }.collectLatest {
                    Log.i("[REQUEST_ARREST]", "$it")
                }
            }
        }
    }
}
