package com.knotworking.misr.databinding

import android.databinding.BindingAdapter
import android.widget.Button
import com.knotworking.misr.R

/**
 * Created on 03.10.17.
 */
object ButtonBindingAdaptors {
    @JvmStatic
    @BindingAdapter(value = *arrayOf("timeSpent", "isTimerRunning"))
    fun setTimerButtonText(button: Button, timeSpent: Long, isTimerRunning: Boolean) {
        val text = when {
            !isTimerRunning && timeSpent == 0L -> R.string.button_start
            !isTimerRunning && timeSpent > 0L -> R.string.button_resume
            else -> R.string.button_stop
        }

        button.setText(text)
    }
}