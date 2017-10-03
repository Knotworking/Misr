package com.knotworking.misr.timer

/**
 * Created by BRL on 23/08/17.
 */
interface TimerActions {

    fun onStartStopClick()

    fun onResetClick()

    fun isTimerRunning(): Boolean
}