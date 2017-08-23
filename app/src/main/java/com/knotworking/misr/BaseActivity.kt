package com.knotworking.misr

import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager

open class BaseActivity : AppCompatActivity() {

    protected lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
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
