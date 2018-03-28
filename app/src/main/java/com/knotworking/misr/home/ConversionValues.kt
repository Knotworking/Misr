package com.knotworking.misr.home

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.databinding.BindingAdapter
import android.text.TextUtils
import android.widget.EditText
import android.widget.Toast
import com.knotworking.misr.BR
import com.knotworking.misr.Utils
import java.text.NumberFormat

/**
 * Created on 03/09/17.
 */
data class ConversionValues(
        @Bindable
        var hours: Int? = null,
        @Bindable
        var minutes: Int? = null,
        @Bindable
        var seconds: Int? = null,
        @Bindable
        var money: Float? = null) : BaseObservable() {

    fun getHoursString() = if (hours != null && hours!! > 0) hours.toString() else ""

    fun getMinutesString() = if (minutes != null && minutes!! > 0) minutes.toString() else ""

    fun getSecondsString() = if (seconds != null && seconds!! > 0) seconds.toString() else ""

    fun getMoneyString() = if (money != null && money!! > 0f) {
        val moneyFormat = Utils.getMoneyFormat(false)
        moneyFormat.format(money)
    } else ""

    fun setHoursString(hours: String) {
        if (!TextUtils.isEmpty(hours)) {
            this.hours = hours.toInt()
        } else {
            this.hours = 0
        }
//        notifyPropertyChanged(BR.hours)
        notifyChange()
    }

    fun setMinutesString(minutes: String) {
        if (!TextUtils.isEmpty(minutes)) {
            this.minutes = minutes.toInt()
        } else {
            this.minutes = 0
        }
//        notifyPropertyChanged(BR.minutes)
        notifyChange()
    }

    fun setSecondsString(seconds: String) {
        if (!TextUtils.isEmpty(seconds)) {
            this.seconds = seconds.toInt()
        } else {
            this.seconds = 0
        }
//        notifyPropertyChanged(BR.seconds)
        notifyChange()
    }

    fun setMoneyString(money: String) {
        if (!TextUtils.isEmpty(money)) {
            //FIXME crashes for DE number format.
            // will be fixed by refactoring conversion logic
            this.money = money.toFloat()
        } else {
            this.money = 0f
        }
//        notifyPropertyChanged(BR.money)
        notifyChange()
    }
}
