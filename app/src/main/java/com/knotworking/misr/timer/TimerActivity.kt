package com.knotworking.misr.timer

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import android.widget.Toast
import com.knotworking.misr.BaseActivity
import com.knotworking.misr.R
import com.knotworking.misr.databinding.ActivityTimerBinding
import java.util.*
import java.util.concurrent.TimeUnit

class TimerActivity : BaseActivity(), TimerActions {
    private val LAST_TICK = "last_tick"
    private val TIME_SPENT = "time_spent"
    private val TIMER_RUNNING = "timer_running"

    private var timer = Timer()
    private val time = Time()
    private lateinit var binding: ActivityTimerBinding
    private var isTimerRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_timer)
        setTime(savedInstanceState)
        isTimerRunning = savedInstanceState?.getBoolean(TIMER_RUNNING) ?: false
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.user = getUser()
        binding.time = time
        binding.action = this

        if (isTimerRunning) startTimer()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putLong(LAST_TICK, time.lastTick)
        outState?.putLong(TIME_SPENT, time.spent)
        outState?.putBoolean(TIMER_RUNNING, isTimerRunning)
    }

    override fun onPause() {
        super.onPause()
        timer.cancel()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onBackPressed() {
        timer.cancel()
        super.onBackPressed()
    }

    override fun onStartClick() {
        startTimer()
        Toast.makeText(this, "lastTick timer", Toast.LENGTH_SHORT).show()
    }

    private fun startTimer() {
        timer = Timer()
        isTimerRunning = true
        time.lastTick = System.currentTimeMillis()
        timer.schedule(MoneyTimer(binding), 0, TimeUnit.SECONDS.toMillis(1))
    }

    override fun onResetClick() {
        timer.cancel()
        Toast.makeText(this, "reset timer", Toast.LENGTH_SHORT).show()
    }

    private fun setTime(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            time.lastTick = savedInstanceState.getLong(LAST_TICK)
            time.spent = savedInstanceState.getLong(TIME_SPENT)
        }
    }

}
