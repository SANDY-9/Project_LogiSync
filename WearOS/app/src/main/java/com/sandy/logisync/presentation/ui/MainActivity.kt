/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.sandy.logisync.presentation.ui

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.sandy.logisync.presentation.ui.screens.PermissionScreen
import com.sandy.logisync.presentation.ui.screens.WatchScreen
import com.sandy.logisync.presentation.ui.theme.LogisyncWearTheme
import com.sandy.logisync.wearable.health.HeartRateServiceManager
import com.sandy.logisync.wearable.service.MyWearableListenerService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    @Inject
    lateinit var healthClient: HeartRateServiceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        requestPermission()
        setTheme(android.R.style.Theme_DeviceDefault)
        setContent(content = LogiSyncWearApp())
        subscribeToObservers()
    }

    override fun onDestroy() {
        //commandHeartRateService(STOP_HEART_RATE_SENSOR)
        // healthClient.unregisterHeartRateCallback()
        super.onDestroy()
    }

    private fun startMyWearableListenerService() {
        val intent = Intent(this, MyWearableListenerService::class.java)
        startService(intent)
    }

    private fun requestPermission() {
        val permissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.BODY_SENSORS,
        )
        multiplePermissionLauncher.launch(permissions)
    }

    private val multiplePermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) {
        val isNotGranted = it.values.contains(false)
        if (!isNotGranted) {
            backgroundLocationPermissionLauncher.launch(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
        }
        else {
            mainViewModel.updateGrantedPermission(false)
        }
    }

    private val backgroundLocationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                backgroundBioPermissionLauncher.launch(Manifest.permission.BODY_SENSORS_BACKGROUND)
            }
            else {
                mainViewModel.updateGrantedPermission(true)
            }
        }
        else {
            mainViewModel.updateGrantedPermission(false)
        }
    }

    private val backgroundBioPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        mainViewModel.updateGrantedPermission(isGranted)
    }

    private fun subscribeToObservers() {
        mainViewModel.isGrantedPermission
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { isGranted ->
                //if (isGranted) PassiveDataMonitoringWorker.registerWorker(this)
            }
            .launchIn(lifecycleScope)
    }

    private fun LogiSyncWearApp(): @Composable () -> Unit = {
        val measuredHeartRate by mainViewModel.measuredHeartRate.collectAsState()
        val isGrantedPermission by mainViewModel.isGrantedPermission.collectAsState()
        LogisyncWearTheme {
            if (isGrantedPermission) {
                WatchScreen(measuredHeartRate)
            }
            else {
                PermissionScreen(onPermission = this::requestPermission)
            }
        }

    }
}
