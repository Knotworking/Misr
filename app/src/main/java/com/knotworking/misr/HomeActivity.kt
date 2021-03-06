package com.knotworking.misr

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.text.InputFilter
import android.view.Menu
import android.view.MenuItem
import com.knotworking.misr.databinding.ActivityHomeBinding
import com.knotworking.misr.timer.TimerActivity
import com.knotworking.misr.utils.InputFilterMinMax
import java.util.*
import java.util.concurrent.TimeUnit

class HomeActivity : BaseActivity() {
    private val SETUP_REQUEST = 1
    private val timer = Timer()

    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        checkUser()
        binding.timerFab.setOnClickListener({
            startActivity(Intent(this, TimerActivity::class.java))
        })

        binding.minutesEditText.filters = arrayOf<InputFilter>(InputFilterMinMax("0", "60"))
        binding.secondsEditText.filters = arrayOf<InputFilter>(InputFilterMinMax("0", "60"))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu?.findItem(R.id.action_edit_user)?.let { Utils.tintMenuIcon(this, it, R.color.toolbar_action) }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_edit_user -> startActivityForResult(Intent(this, SetupActivity::class.java), SETUP_REQUEST)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun checkUser() {
        if (isUserSetUp()) {
            binding.user = getUser()
            startTimer()
        } else {
            startActivityForResult(Intent(this, SetupActivity::class.java), SETUP_REQUEST)
        }
    }

    private fun startTimer() {
        timer.schedule(MoneyClock(binding), 0, TimeUnit.SECONDS.toMillis(1))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == SETUP_REQUEST && isUserSetUp()) {
            checkUser()
        } else {
            finish()
        }

        super.onActivityResult(requestCode, resultCode, data)
    }
}
