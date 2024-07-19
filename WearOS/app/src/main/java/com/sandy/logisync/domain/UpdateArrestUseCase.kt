package com.sandy.logisync.domain

import android.location.Location
import com.sandy.logisync.data.network.NetworkRepository
import com.sandy.logisync.model.Arrest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateArrestUseCase @Inject constructor(
    private val networkRepository: NetworkRepository,
) {
    suspend operator fun invoke(
        id: String,
        arrestType: Arrest.ArrestType,
        location: Location,
        bpm: Int,
    ): Flow<Boolean> {
        return networkRepository.updateHeartBeatArrest(id, arrestType, location, bpm)
    }
}
