package com.sandy.logisync.data.location

import android.annotation.SuppressLint
import android.location.Location
import com.google.android.gms.location.CurrentLocationRequest
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnTokenCanceledListener
import javax.inject.Inject

class LocationClient @Inject constructor(
    private val fusedLocationProviderClient: FusedLocationProviderClient,
) {

    private val currentLocationRequest = CurrentLocationRequest.Builder()
        .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
        .build()
    private val cancellationToken = object : CancellationToken() {
        override fun onCanceledRequested(p0: OnTokenCanceledListener) = CancellationTokenSource().token
        override fun isCancellationRequested(): Boolean = false
    }

    @SuppressLint("MissingPermission")
    fun getLastLocation(
        onSuccess: (Location) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        fusedLocationProviderClient.getCurrentLocation(
            currentLocationRequest,
            cancellationToken
        ).addOnSuccessListener { location ->
           onSuccess(location)
        }.addOnFailureListener { error ->
           onFailure(error)
        }
    }

}
