package com.sandy.logisync.domain

import com.sandy.logisync.data.location.LocationRepository
import com.sandy.logisync.data.network.NetworkRepository
import com.sandy.logisync.model.Arrest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

class RequestNormalMyArrestUseCase @Inject constructor (
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
            networkRepository.notifyMyArrest(id, name, tel, Arrest.ArrestType.NORMAL, location)
        }
    }

}
