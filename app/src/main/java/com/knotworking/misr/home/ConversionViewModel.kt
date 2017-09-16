package com.knotworking.misr.home

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.knotworking.misr.BR

/**
 * Created on 03/09/17.
 */
class ConversionViewModel: BaseObservable(){
    //TODO two way view bindings set each other
    // change back to data class, do logic in binding adapters

    private var hours: Int? = null
    private var minutes: Int? = null
    private var seconds: Int? = null
    private var money: Float? = null

    @Bindable
    fun getHours() = hours?.toString()

    @Bindable
    fun getMinutes() = minutes?.toString()

    @Bindable
    fun getSeconds() = seconds?.toString()

    @Bindable
    fun getMoney() = money?.toString()

    fun setHours(hours: String) {
        this.hours = hours.toInt()
        updateMoney()
    }

    fun setMinutes(minutes: String) {
        this.minutes = minutes.toInt()
        updateMoney()
    }

    fun setSeconds(seconds: String) {
        this.seconds = seconds.toInt()
        updateMoney()
    }

    fun setMoney(money: String) {
        this.money = money.toFloat()
        updateTime()
    }

    private fun updateMoney() {
        money = 600.0f
        notifyPropertyChanged(BR.money)
    }

    private fun updateTime() {
        hours = 1
        minutes = 2
        seconds = 3
        notifyChange()
    }
}