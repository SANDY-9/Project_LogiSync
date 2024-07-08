package com.sandy.logisync.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat

object PermissionManager {

    val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.BODY_SENSORS,
            //Manifest.permission.ACCESS_BACKGROUND_LOCATION,
            //Manifest.permission.BODY_SENSORS_BACKGROUND,
        )
    }
    else {
        arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_BACKGROUND_LOCATION,
            Manifest.permission.BODY_SENSORS,
        )
    }

    private fun isMultiplePermissionGranted(context: Context): Boolean {
        return permissions.all { it.isGratedPermission(context) }
    }

    private fun String.isGratedPermission(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(context, this) == PackageManager.PERMISSION_GRANTED
    }
}
