package com.sandy.logisync.domain

import android.location.Location
import com.sandy.logisync.data.location.LocationRepository
import com.sandy.logisync.data.network.NetworkRepository
import com.sandy.logisync.model.Arrest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

class RequestNormalArrestUseCase @Inject constructor (
    private val networkRepository: NetworkRepository,
    private val locationRepository: LocationRepository,
) {

    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(id: String): Flow<String?> {
        return locationRepository.getLastLocation().flatMapLatest { location ->
            updateArrestToServer(id, location)
            networkRepository.notifyArrest(id)
        }
    }

    private suspend fun updateArrestToServer(
        id: String,
        location: Location,
    ) {
        coroutineScope {
            networkRepository.updateNormalArrest(id, Arrest.ArrestType.NORMAL, location).first()
        }
    }

}
