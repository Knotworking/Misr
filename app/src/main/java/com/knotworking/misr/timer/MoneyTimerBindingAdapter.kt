package com.knotworking.misr.timer

import android.databinding.BindingAdapter
import android.widget.TextView
import java.text.DecimalFormat

/**
 * Created by BRL on 24/08/17.
 */
object MoneyTimerBindingAdapter {
    @JvmStatic
    @BindingAdapter(value = *arrayOf("salary", "currency", "timeSpent"), requireAll = true)
    fun setCurrentCount(textView: TextView, salary: Float, currency: String, timeSpent: Long) {
        /*
        Values to pass: currentCount, startTime

        set startTime on click
        calculate using the difference between startTime and currentTime
        add the result to the current total
         */

        val decimalFormat = DecimalFormat.getInstance()
        decimalFormat.minimumFractionDigits = 2
        decimalFormat.maximumFractionDigits = 2

        val currentCount = payPerHour(salary) * hoursFromMillis(timeSpent)

        textView.text = currency + decimalFormat.format(currentCount)
    }

    private fun payPerHour(salary: Float) = salary / 120

    private fun hoursFromMillis(millis: Long): Float = millis.toFloat() / 1000 / 60 / 60
}