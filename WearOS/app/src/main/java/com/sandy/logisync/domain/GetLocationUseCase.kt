package com.sandy.logisync.domain

import android.location.Location
import com.sandy.logisync.data.location.LocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(
    private val locationRepository: LocationRepository,
) {

    operator fun invoke(): Flow<Location> {
        return locationRepository.getLastLocation()
    }

}
