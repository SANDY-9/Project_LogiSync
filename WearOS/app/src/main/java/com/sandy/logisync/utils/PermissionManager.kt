package com.sandy.logisync.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat

object PermissionManager {

    private val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
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

    fun checkAndRequestPermissions(
        context: Context,
        launcher: ActivityResultLauncher<Array<String>>,
    ) {
        /** 권한이 이미 있는 경우 **/
        if (isMultiplePermissionGranted(context)) {
            Log.e("확인", "권한이 이미 존재합니다.")
        }

        /** 권한이 없는 경우 **/
        else {
            launcher.launch(permissions)
        }
    }

    private fun isMultiplePermissionGranted(context: Context): Boolean {
        return permissions.all { it.isGratedPermission(context) }
    }

    private fun String.isGratedPermission(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(context, this) == PackageManager.PERMISSION_GRANTED
    }
}
