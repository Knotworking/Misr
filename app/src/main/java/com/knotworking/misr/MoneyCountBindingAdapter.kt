package com.knotworking.misr

import android.databinding.BindingAdapter
import android.widget.TextView

/**
 * Created by BRL on 29/07/17.
 */
object MoneyCountBindingAdapter {
    @JvmStatic
    @BindingAdapter("salary")
    fun setDailyCount(textView: TextView, salary: Float) {
        textView.text = "binded salary: " + salary.toString();
    }
}