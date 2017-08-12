package com.knotworking.misr

import android.databinding.BindingAdapter
import android.widget.TextView
import java.text.DecimalFormat
import java.util.*

/**
 * Created by BRL on 29/07/17.
 */
object MoneyCountBindingAdapter {
    val DAYS_IN_YEAR = 365
    val HOURS_IN_DAY = 24
    val MINUTES_IN_HOUR = 60
    val SECONDS_IN_DAY = 86400
    val MILLISECONDS_IN_SECOND = 1000

    @JvmStatic
    @BindingAdapter(value = *arrayOf("salary", "currency"), requireAll = false)
    fun setDailyCount(textView: TextView, salary: Float, currency: String) {
        val decimalFormat = DecimalFormat.getInstance()
        decimalFormat.minimumFractionDigits = 2
        decimalFormat.maximumFractionDigits = 2
        textView.text = "Daily Earnings: ${currency + decimalFormat.format(getMoneyEarnedSoFarToday(salary))}"
    }

    private fun getMoneyEarnedSoFarToday(salary: Float): Float {
        val earnedPerDay = getMoneyEarnedPerDay(salary)
        return earnedPerDay * getDailyProgress()
    }

    private fun getDailyProgress(): Float {
        val calendar = Calendar.getInstance()
        val now = calendar.timeInMillis
        setCalendarToMidnight(calendar)
        val passedMilliseconds = now - calendar.timeInMillis
        val passedSeconds = passedMilliseconds.toFloat() / MILLISECONDS_IN_SECOND
        return passedSeconds / SECONDS_IN_DAY
    }

    private fun setCalendarToMidnight(calendar: Calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
    }

    private fun getMoneyEarnedPerDay(salary: Float): Float {
        val yearly = salary * 12
        return yearly / DAYS_IN_YEAR
    }
}