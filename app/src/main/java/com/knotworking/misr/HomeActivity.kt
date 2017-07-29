package com.knotworking.misr

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.knotworking.misr.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityHomeBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_home)

        binding.user = User(salary = 2500.00f)
    }
}
