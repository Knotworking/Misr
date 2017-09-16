package com.knotworking.misr.home

import com.knotworking.misr.databinding.ActivityHomeBinding
import java.util.*

/**
 * Created by BRL on 07/08/17.
 */
class MoneyClock(val binding: ActivityHomeBinding) : TimerTask() {
    override fun run() {
        //TODO find cheaper method call
        binding.invalidateAll()
    }
}