package com.knotworking.misr.home

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.databinding.BindingAdapter
import android.widget.EditText
import android.widget.Toast
import com.knotworking.misr.BR

/**
 * Created on 03/09/17.
 */
data class Conversion(
        @Bindable
        var hours: Int? = null,
        @Bindable
        var minutes: Int? = null,
        @Bindable
        var seconds: Int? = null,
        @Bindable
        var money: Float? = null) : BaseObservable() {

    fun getHoursString() = hours?.toString()

    fun getMinutesString() = minutes?.toString()

    fun getSecondsString() = seconds?.toString()

    fun getMoneyString() = money?.toString()

    fun setHoursString(hours: String) {
        this.hours = hours.toInt()
        notifyPropertyChanged(BR.hours)
    }

    fun setMinutesString(minutes: String) {
        this.minutes = minutes.toInt()
        notifyPropertyChanged(BR.minutes)
    }

    fun setSecondsString(seconds: String) {
        this.seconds = seconds.toInt()
        notifyPropertyChanged(BR.seconds)
    }

    fun setMoneyString(money: String) {
        this.money = money.toFloat()
        notifyPropertyChanged(BR.money)
    }
}
