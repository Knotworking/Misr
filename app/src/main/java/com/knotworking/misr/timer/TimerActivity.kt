package com.knotworking.misr.timer

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.knotworking.misr.BaseActivity
import com.knotworking.misr.R
import com.knotworking.misr.databinding.ActivityTimerBinding

class TimerActivity : BaseActivity(), TimerActions {

    private lateinit var binding: ActivityTimerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_timer)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.user = getUser()
        binding.action = this
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStartClick() {
        Toast.makeText(this, "start timer", Toast.LENGTH_SHORT).show()
    }

    override fun onResetClick() {
        Toast.makeText(this, "reset timer", Toast.LENGTH_SHORT).show()
    }

}
