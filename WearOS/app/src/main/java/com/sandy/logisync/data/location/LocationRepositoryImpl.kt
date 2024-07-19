package com.sandy.logisync.data.location

import android.location.Location
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val locationClient: LocationClient,
) : LocationRepository {
    override fun getLastLocation(): Flow<Location> = callbackFlow {
        locationClient.getLastLocation(
            onSuccess = { location ->
                trySend(location)
            },
            onFailure = { e ->
                close(e)
            },
        )
        awaitClose()
    }
}
