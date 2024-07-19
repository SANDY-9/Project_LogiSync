package com.sandy.logisync.data.location

import android.location.Location
import kotlinx.coroutines.flow.Flow

interface LocationRepository {

    fun getLastLocation(): Flow<Location>

}
