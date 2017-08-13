package com.knotworking.misr

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.knotworking.misr.databinding.ActivityHomeBinding
import java.util.*

class HomeActivity : AppCompatActivity() {
    val SETUP_REQUEST = 1
    val timer = Timer()

    lateinit var binding: ActivityHomeBinding
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        sharedPreferences = getSharedPreferences(Constants.SHARED_PREFS, Context.MODE_PRIVATE)
        getUser()
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

    private fun getUser() {
        if (isUserSetUp()) {
            val name = sharedPreferences.getString(Constants.NAME, "")
            val salary = sharedPreferences.getFloat(Constants.SALARY, 0f)
            val currency = sharedPreferences.getString(Constants.CURRENCY, "$")
            binding.user = User(name, salary, currency)
            startTimer()
        } else {
            startActivityForResult(Intent(this, SetupActivity::class.java), SETUP_REQUEST)
        }
    }

    private fun startTimer() {
        timer.schedule(MoneyTimer(binding), 0, 1000)
    }

    private fun isUserSetUp(): Boolean {
        return sharedPreferences.contains(Constants.NAME) &&
                sharedPreferences.contains(Constants.SALARY) &&
                sharedPreferences.contains(Constants.CURRENCY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == SETUP_REQUEST && isUserSetUp()) {
            getUser()
        } else {
            finish()
        }

        super.onActivityResult(requestCode, resultCode, data)
    }
}
