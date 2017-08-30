package com.knotworking.misr.timer

import android.os.Bundle
import android.view.MenuItem
import com.knotworking.misr.BaseActivity
import com.knotworking.misr.R

class TimerActivity : BaseActivity() {
    private val TIMER_FRAGMENT = "TIMER_FRAGMENT"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            val fragment = TimerFragment()
            supportFragmentManager.beginTransaction().add(R.id.activity_timer_root, fragment, TIMER_FRAGMENT).commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
