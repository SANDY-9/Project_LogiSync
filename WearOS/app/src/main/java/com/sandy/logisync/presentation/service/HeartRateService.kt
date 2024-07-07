package com.sandy.logisync.presentation.service

import android.app.Service
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.IBinder
import android.util.Log
import com.sandy.logisync.presentation.common.START_HEART_RATE_SENSOR
import com.sandy.logisync.presentation.common.STOP_HEART_RATE_SENSOR
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeartRateService : Service(), SensorEventListener {

    private var sensorManager: SensorManager? = null

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Log.e("확인", "onCreate: 확인")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            when (it.action) {
                START_HEART_RATE_SENSOR -> startHeartRateSensor()
                STOP_HEART_RATE_SENSOR -> stopSelf()
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun startHeartRateSensor() {
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        val sensor = sensorManager?.getDefaultSensor(Sensor.TYPE_HEART_RATE)
        sensorManager?.registerListener(
            this,
            sensor,
            SensorManager.SENSOR_DELAY_NORMAL,
        )
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val hr = event?.values?.get(0)
        Log.e("확인", "onSensorChanged: $hr")
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        Log.e("확인", "onAccuracyChanged: $accuracy")
    }

    override fun onDestroy() {
        Log.e("확인", "onDestroy: 확인")
        sensorManager?.unregisterListener(this)
        sensorManager = null
        super.onDestroy()
    }
}
