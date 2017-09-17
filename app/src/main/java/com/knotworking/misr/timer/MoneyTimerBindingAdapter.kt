package com.knotworking.misr.timer

import android.databinding.BindingAdapter
import android.widget.TextView
import com.knotworking.misr.Utils
import java.text.DecimalFormat

/**
 * Created by BRL on 24/08/17.
 */
object MoneyTimerBindingAdapter {
    @JvmStatic
    @BindingAdapter(value = *arrayOf("salary", "currency", "timeSpent"), requireAll = true)
    fun setCurrentCount(textView: TextView, salary: Float, currency: String, timeSpent: Long) {
        val decimalFormat = DecimalFormat.getInstance()
        decimalFormat.minimumFractionDigits = 2
        decimalFormat.maximumFractionDigits = 2

        val currentCount = Utils.payPerHour(salary) * Utils.hoursFromMillis(timeSpent)

        textView.text = currency + decimalFormat.format(currentCount)
    }
}