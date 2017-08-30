package com.knotworking.misr.timer

import com.knotworking.misr.databinding.ActivityTimerBinding
import com.knotworking.misr.databinding.FragmentTimerBinding
import java.util.*

/**
 * Created by BRL on 24/08/17.
 */
class MoneyTimer(val binding: FragmentTimerBinding) : TimerTask() {
    override fun run() {
        /*
        update timer()

        perhaps do the calculation here
         */
        binding.time.spent += System.currentTimeMillis() - binding.time.lastTick
        binding.time.lastTick = System.currentTimeMillis()

        //TODO find cheaper method call
        binding.invalidateAll()
    }
}