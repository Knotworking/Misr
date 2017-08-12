package com.knotworking.misr

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.knotworking.misr.databinding.ActivityHomeBinding
import java.util.*

class HomeActivity : AppCompatActivity() {
    val SETUP_REQUEST = 1
    val timer = Timer()

    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

//        binding.user = User("Bob", 2500.00f, "€")

        getUser()
    }

    private fun getUser() {
        val sharedPreferences = getSharedPreferences(Constants.SHARED_PREFS, Context.MODE_PRIVATE)
        if (sharedPreferences.contains(Constants.NAME) &&
                sharedPreferences.contains(Constants.SALARY) &&
                sharedPreferences.contains(Constants.CURRENCY)) {
            val name = sharedPreferences.getString(Constants.NAME, "")
            val salary = sharedPreferences.getFloat(Constants.SALARY, 0f)
            val currency = sharedPreferences.getString(Constants.CURRENCY, "$")
            binding.user = User(name, salary, currency)
            startTimer()
        } else {
            startActivityForResult(Intent(this, SetupActivity::class.java), SETUP_REQUEST)
        }
    }

    private fun startTimer() {
        timer.schedule(MoneyTimer(binding), 0, 1000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == SETUP_REQUEST && resultCode == Activity.RESULT_OK) {
            getUser()
        } else {
            finish()
        }

        super.onActivityResult(requestCode, resultCode, data)
    }
}
