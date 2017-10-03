package com.knotworking.misr.timer

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.knotworking.misr.BaseFragment
import com.knotworking.misr.R
import com.knotworking.misr.databinding.FragmentTimerBinding
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by BRL on 29/08/17.
 */
class TimerFragment : BaseFragment(), TimerActions {
    private val LAST_TICK = "last_tick"
    private val TIME_SPENT = "time_spent"
    private val TIMER_RUNNING = "timer_running"

    private var timer = Timer()
    private val time = Time()
    private var timerRunning = false
    private lateinit var binding: FragmentTimerBinding

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_timer, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setTime(savedInstanceState)
        timerRunning = savedInstanceState?.getBoolean(TIMER_RUNNING) ?: false
        binding.user = getUser()
        binding.time = time
        binding.action = this

        if (timerRunning) startTimer()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putLong(LAST_TICK, time.lastTick)
        outState?.putLong(TIME_SPENT, time.spent)
        outState?.putBoolean(TIMER_RUNNING, timerRunning)
    }

    override fun onPause() {
        super.onPause()
        timer.cancel()
    }

    override fun onStartStopClick() {
        if (timerRunning) {
            stopTimer()
        } else {
            startTimer()
        }
        time.notifyChange()
    }

    private fun startTimer() {
        timer = Timer()
        timerRunning = true
        time.lastTick = System.currentTimeMillis()
        timer.schedule(MoneyTimer(binding), 0, TimeUnit.SECONDS.toMillis(1))
    }

    private fun stopTimer() {
        timer.cancel()
        timerRunning = false
    }

    override fun onResetClick() {
        stopTimer()
        time.spent = 0
        time.notifyChange()
    }

    override fun isTimerRunning(): Boolean = timerRunning

    private fun setTime(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            time.lastTick = savedInstanceState.getLong(LAST_TICK)
            time.spent = savedInstanceState.getLong(TIME_SPENT)
        }
    }
}