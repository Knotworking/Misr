package com.knotworking.misr.databinding

import android.databinding.BindingAdapter
import android.util.Log
import android.widget.EditText

/**
 * Created on 16/09/2017.
 */
object EditTextBindingAdapters {
    @JvmStatic
    @BindingAdapter(value = *arrayOf("hours", "minutes", "seconds", "moneyText"))
    fun updateMoney(editText: EditText, hours: Int?, minutes: Int?, seconds: Int?, moneyEditText: EditText) {
        if (editText.isFocused) {
            val money = moneyEditText.text
            Log.i("TAG", "time changed. H:$hours M:$minutes S:$seconds MoneyText:$money")
            //Do conversion logic
        }
    }

    @JvmStatic
    @BindingAdapter(value = *arrayOf("money", "timeText"))
    fun updateTime(editText: EditText, money: Float?, timeEditText: EditText) {
        if (editText.isFocused) {
            Log.i("TAG", "money changed: $money")
            //Do conversion logic
        }
    }
}