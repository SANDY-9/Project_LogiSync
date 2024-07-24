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
    operator fun invoke(
        id: String,
        name: String,
        tel: String,
    ): Flow<String?> {
        return locationRepository.getLastLocation().flatMapLatest { location ->
            updateArrestToServer(id, name, tel, location)
            networkRepository.notifyArrest(id, name, tel, Arrest.ArrestType.NORMAL, location)
        }
    }
    private suspend fun updateArrestToServer(
        id: String,
        name: String,
        tel: String,
        location: Location,
    ) {
        coroutineScope {
            networkRepository.updateNormalArrest(
                id = id,
                name = name,
                tel = tel,
                arrestType = Arrest.ArrestType.NORMAL,
                location = location
            ).first()
        }
    }

}
