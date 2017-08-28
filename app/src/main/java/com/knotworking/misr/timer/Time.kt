package com.knotworking.misr.timer

import android.databinding.BaseObservable

/**
 * Created by BRL on 25/08/17.
 */
data class Time(var lastTick: Long = 0, var spent: Long = 0) : BaseObservable()