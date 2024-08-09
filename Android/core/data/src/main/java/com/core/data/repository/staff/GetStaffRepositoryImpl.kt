package com.core.data.repository.staff

import android.util.Log
import com.core.domain.repository.GetStaffRepository
import com.core.firebase.StaffClient
import com.core.model.Staff
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

internal class GetStaffRepositoryImpl @Inject constructor (
    private val staffClient: StaffClient,
) : GetStaffRepository {
    override fun getStaffList(): Flow<List<Staff>> = callbackFlow {
        staffClient.getStaffList(
            onSuccess = {
                trySend(it)
                Log.e("확인", "getStaffList: $it", )
            },
            onError = {
                close(it)
            }
        )
        awaitClose()
    }
}
