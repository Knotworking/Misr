package com.knotworking.misr.databinding

import android.databinding.BindingAdapter
import android.util.Log
import android.widget.EditText
import com.knotworking.misr.Constants.MINUTES_IN_HOUR
import com.knotworking.misr.Constants.SECONDS_IN_MINUTE
import com.knotworking.misr.Utils
import com.knotworking.misr.home.ConversionValues

/**
 * Created on 16/09/2017.
 */
object EditTextBindingAdapters {
    @JvmStatic
    @BindingAdapter(value = *arrayOf("fromTime", "salary"))
    fun updateMoney(editText: EditText, conversionValues: ConversionValues, salary: Float) {
        if (editText.isFocused) {
            Log.i("TAG", "time changed")

            var hours: Float = if (conversionValues.hours != null) conversionValues.hours!!.toFloat() else 0f
            hours += if (conversionValues.minutes != null) Utils.hoursFromMinutes(conversionValues.minutes!!) else 0f
            hours += if (conversionValues.seconds != null) Utils.hoursFromSeconds(conversionValues.seconds!!) else 0f

            conversionValues.money = Utils.payPerHour(salary) * hours
            conversionValues.notifyChange()
        }
    }

    @JvmStatic
    @BindingAdapter(value = *arrayOf("fromMoney", "salary"))
    fun updateTime(editText: EditText, conversionValues: ConversionValues, salary: Float) {
        if (editText.isFocused) {
            Log.i("TAG", "money changed")

            //TODO refactor
            var time = conversionValues.money?.div(Utils.payPerHour(salary))
            if (time != null) {

                var temp = time.toInt()
                conversionValues.hours = temp
                time -= temp

                time *= MINUTES_IN_HOUR
                temp = time.toInt()
                conversionValues.minutes = temp
                time -= temp

                time *= SECONDS_IN_MINUTE
                temp = time.toInt()
                conversionValues.seconds = temp

            } else {
                conversionValues.hours = null
                conversionValues.minutes = null
                conversionValues.seconds = null
            }

            conversionValues.notifyChange()
        }
    }
}