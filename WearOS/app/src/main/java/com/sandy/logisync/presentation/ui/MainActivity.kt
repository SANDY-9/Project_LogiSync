/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.sandy.logisync.presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.sandy.logisync.presentation.common.START_HEART_RATE_SENSOR
import com.sandy.logisync.presentation.common.STOP_HEART_RATE_SENSOR
import com.sandy.logisync.presentation.service.HeartRateService
import com.sandy.logisync.presentation.ui.screens.WatchScreen
import com.sandy.logisync.presentation.ui.theme.LogisyncWearTheme
import com.sandy.logisync.utils.PermissionManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        requestPermission()

        setTheme(android.R.style.Theme_DeviceDefault)
        setContent { WearApp() }
    }

    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) {
        val isNotGranted = it.values.contains(false)
    }

    private fun requestPermission() {
        permissionLauncher.launch(PermissionManager.permissions)
    }

    }
}

@Composable
fun WearApp() {
    LogisyncWearTheme {
        WatchScreen()
    }
}
