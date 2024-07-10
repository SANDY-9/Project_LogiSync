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
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.sandy.logisync.presentation.common.START_HEART_RATE_SENSOR
import com.sandy.logisync.presentation.common.STOP_HEART_RATE_SENSOR
import com.sandy.logisync.presentation.ui.screens.WatchScreen
import com.sandy.logisync.presentation.ui.theme.LogisyncWearTheme
import com.sandy.logisync.service.HeartRateService
import com.sandy.logisync.utils.PermissionManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        requestPermission()
        setTheme(android.R.style.Theme_DeviceDefault)
        setContent(content = connectWearApp())
        subscribeToObservers()
    }

    override fun onDestroy() {
        commandHeartRateService(STOP_HEART_RATE_SENSOR)
        super.onDestroy()
    }

    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) {
        val isNotGranted = it.values.contains(false)
        if (!isNotGranted) {
            commandHeartRateService(START_HEART_RATE_SENSOR)
        }
    }

    private fun requestPermission() {
        permissionLauncher.launch(PermissionManager.permissions)
    }

    private fun commandHeartRateService(command: String) {
        val intent = Intent(this, HeartRateService::class.java).apply {
            action = command
        }
        startService(intent)
    }

    private fun subscribeToObservers() {
        HeartRateService.heartRate.observe(this) { rate ->
            mainViewModel.updateHeartRate(rate)
        }
    }

    private fun connectWearApp(): @Composable () -> Unit {
        return {
            val heartRate by mainViewModel.hearRate.collectAsState()
            LogisyncWearTheme {
                WatchScreen(heartRate = heartRate)
            }
        }
    }
}
