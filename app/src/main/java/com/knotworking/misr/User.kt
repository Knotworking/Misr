package com.knotworking.misr

import android.databinding.BaseObservable

/**
 * Created by BRL on 29/07/17.
 */
data class User(val name: String = "John", val salary: Float) : BaseObservable()