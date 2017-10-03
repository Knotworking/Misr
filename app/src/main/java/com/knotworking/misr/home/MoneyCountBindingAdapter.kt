package com.knotworking.misr.home

import android.databinding.BindingAdapter
import android.widget.TextView
import com.knotworking.misr.Utils
import java.text.DecimalFormat
import java.util.*

/**
 * Created by BRL on 29/07/17.
 */
object MoneyCountBindingAdapter {
    private val DAYS_IN_YEAR = 365
    private val SECONDS_IN_DAY = 86400
    private val MILLISECONDS_IN_SECOND = 1000

    @JvmStatic
    @BindingAdapter(value = *arrayOf("salary", "currency"), requireAll = true)
    fun setDailyCount(textView: TextView, salary: Float, currency: String) {
        val decimalFormat = Utils.getMoneyFormat(true)
        textView.text = "Earned so far today: ${currency + decimalFormat.format(getMoneyEarnedSoFarToday(salary))}"
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