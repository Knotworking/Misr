package com.knotworking.misr

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment

/**
 * Created by BRL on 29/08/17.
 */
open class BaseFragment : Fragment() {
    protected lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    }

    protected fun isUserSetUp(): Boolean {
        return sharedPreferences.contains(Constants.NAME) &&
                sharedPreferences.contains(Constants.SALARY) &&
                sharedPreferences.contains(Constants.CURRENCY)
    }

    protected fun getUser(): User? {
        if (isUserSetUp()) {
            val name = sharedPreferences.getString(Constants.NAME, "")
            val salary = sharedPreferences.getFloat(Constants.SALARY, 0f)
            val currency = sharedPreferences.getString(Constants.CURRENCY, "$")
            return User(name, salary, currency)
        }

        return null
    }
}