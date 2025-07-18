package com.movemate.shared

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import javax.inject.Inject


class MovemateVibrator @Inject constructor(context: Context) {

    private val vibrator: Vibrator? = context.getSystemService(Vibrator::class.java)

    fun clickVibrate() {
        vibrator?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                it.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK))
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                it.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                it.vibrate(50)
            }
        }
    }

    fun heavyClickVibrate() {
        vibrator?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                it.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_HEAVY_CLICK))
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                it.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                it.vibrate(100)
            }
        }
    }

    fun doubleClickVibrate() {
        vibrator?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                it.vibrate(VibrationEffect.createWaveform(longArrayOf(0, 50, 50, 50), -1))
            } else {
                it.vibrate(longArrayOf(0, 50, 50, 50), -1)
            }
        }
    }

    companion object {
        @Volatile private var INSTANCE: MovemateVibrator? = null

        fun initInstance(context: Context): MovemateVibrator {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: MovemateVibrator(context).also { INSTANCE = it }
            }
        }

        fun getInstance(): MovemateVibrator? {
            return INSTANCE ?: synchronized(this) {
                INSTANCE
            }
        }
    }
}
