
package edu.utp.sensorlab.sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner

data class SensorReading(
    val available: Boolean,
    val values: List<Float> = emptyList(),
    val maxRange: Float = 0f
)

@Composable
fun rememberSensorReading(
    sensorType: Int
): SensorReading {

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val sensorManager = remember(context) {
        context.getSystemService(
            Context.SENSOR_SERVICE
        ) as SensorManager
    }

    val sensor = remember(sensorManager, sensorType) {
        sensorManager.getDefaultSensor(sensorType)
    }

    var values by remember(sensorType) {
        mutableStateOf(emptyList<Float>())
    }

    DisposableEffect(
        sensorManager,
        sensor,
        lifecycleOwner
    ) {

        val listener = object : SensorEventListener {

            override fun onSensorChanged(
                event: SensorEvent
            ) {
                values = event.values.toList()
            }

            override fun onAccuracyChanged(
                sensor: Sensor?,
                accuracy: Int
            ) {
                // No necesitamos este dato por ahora.
            }
        }

        fun startListening() {
            if (sensor != null) {
                sensorManager.registerListener(
                    listener,
                    sensor,
                    SensorManager.SENSOR_DELAY_UI
                )
            }
        }

        fun stopListening() {
            sensorManager.unregisterListener(listener)
        }

        val observer = LifecycleEventObserver {
                _, event ->

            when (event) {
                Lifecycle.Event.ON_START -> {
                    startListening()
                }

                Lifecycle.Event.ON_STOP -> {
                    stopListening()
                }

                else -> Unit
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        if (
            lifecycleOwner.lifecycle.currentState
                .isAtLeast(Lifecycle.State.STARTED)
        ) {
            startListening()
        }

        onDispose {
            stopListening()
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    return SensorReading(
        available = sensor != null,
        values = values,
        maxRange = sensor?.maximumRange ?: 0f
    )
}
