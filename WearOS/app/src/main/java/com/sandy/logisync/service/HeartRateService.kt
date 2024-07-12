package com.sandy.logisync.service

import android.app.Service
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.IBinder
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sandy.logisync.presentation.common.START_HEART_RATE_SENSOR
import com.sandy.logisync.presentation.common.STOP_HEART_RATE_SENSOR
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HeartRateService : Service(), SensorEventListener {

    private var sensorManager: SensorManager? = null

    @Inject
    lateinit var myWearableClient: MyWearableClient

    companion object {
        private val _heartRate = MutableLiveData<Int>()
        val heartRate: LiveData<Int> get() = _heartRate
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("확인", "onStartCommand: 확인")
        intent?.let {
            when (it.action) {
                START_HEART_RATE_SENSOR -> startHeartRateSensor()
                STOP_HEART_RATE_SENSOR -> stopSelf()
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun startHeartRateSensor() {
        myWearableClient.setupConnectApp()
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        val sensor = sensorManager?.getDefaultSensor(Sensor.TYPE_HEART_RATE)
        Log.e("확인", "startHeartRateSensor: $sensor")
        sensorManager?.registerListener(
            this,
            sensor,
            SensorManager.SENSOR_DELAY_NORMAL,
        )
    }

    // 나중에 accuracy == 3일때로 보정하기, 지금은 테스트를 위해 보정 생략
    override fun onSensorChanged(event: SensorEvent?) {
        val rate = event?.values?.get(0)?.toInt()
        rate?.let { rate ->
            //if (rate > 0) _heartRate.postValue(rate)
            _heartRate.postValue(rate)
            Log.e("확인", "onSensorChanged: $rate")
            // myWearableClient.requestTranscription(10.toString().toByteArray())
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        Log.e("확인", "onAccuracyChanged: $accuracy")
    }

    override fun onDestroy() {
        sensorManager?.unregisterListener(this)
        sensorManager = null
        super.onDestroy()
    }
}
