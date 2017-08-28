package com.knotworking.misr.timer

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.knotworking.misr.BaseActivity
import com.knotworking.misr.R
import com.knotworking.misr.databinding.ActivityTimerBinding
import java.util.*
import java.util.concurrent.TimeUnit

class TimerActivity : BaseActivity(), TimerActions {

    private var timer = Timer()
    private val time = Time()
    private lateinit var binding: ActivityTimerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_timer)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.user = getUser()
        binding.time = time
        binding.action = this
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        timer.cancel()
        super.onBackPressed()
    }

    override fun onStartClick() {
        timer = Timer()
        time.lastTick = System.currentTimeMillis()
        timer.schedule(MoneyTimer(binding), 0, TimeUnit.SECONDS.toMillis(1))
        Toast.makeText(this, "lastTick timer", Toast.LENGTH_SHORT).show()
    }

    override fun onResetClick() {
        timer.cancel()
        Toast.makeText(this, "reset timer", Toast.LENGTH_SHORT).show()
    }

}
