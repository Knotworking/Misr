package com.knotworking.misr.databinding

import android.databinding.BindingAdapter
import android.util.Log
import android.widget.EditText
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
            conversionValues.money = 600f
            conversionValues.notifyChange()
            //Do conversionValues logic
        }
    }

    @JvmStatic
    @BindingAdapter(value = *arrayOf("fromMoney", "salary"))
    fun updateTime(editText: EditText, conversionValues: ConversionValues, salary: Float) {
        if (editText.isFocused) {
            Log.i("TAG", "money changed")
            conversionValues.hours = 1
            conversionValues.minutes = 2
            conversionValues.seconds = 3
            conversionValues.notifyChange()
            //Do conversionValues logic
        }
    }
}