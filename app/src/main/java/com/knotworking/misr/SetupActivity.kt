package com.knotworking.misr

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.knotworking.misr.databinding.ActivitySetupBinding

class SetupActivity : AppCompatActivity() {

    private val defaultName = ""
    private val defaultSalary = 0f
    private val defaultCurrency = "$"

    lateinit var binding: ActivitySetupBinding
    lateinit var adapter: ArrayAdapter<CharSequence>
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_setup)

        adapter = ArrayAdapter.createFromResource(this, R.array.currency_array, android.R.layout.simple_spinner_item)

        binding.currencySpinner.adapter = adapter

        sharedPreferences = getSharedPreferences(Constants.SHARED_PREFS, Context.MODE_PRIVATE)

        binding.nameEditText.setText(sharedPreferences.getString(Constants.NAME, defaultName))
        binding.salaryEditText.setText(sharedPreferences.getFloat(Constants.SALARY, defaultSalary).toString())
        val currencyIndex = adapter.getPosition(sharedPreferences.getString(Constants.CURRENCY, defaultCurrency))
        binding.currencySpinner.setSelection(currencyIndex)

        binding.saveButton.setOnClickListener({
            saveUserValues()
            setResult(Activity.RESULT_OK)
            finish()
        })
    }

    private fun saveUserValues() {
        val name = binding.nameEditText.text.toString()
        val salary = binding.salaryEditText.text.toString().toFloat()
        val currency = adapter.getItem(binding.currencySpinner.selectedItemPosition).toString()

        sharedPreferences.edit()
                .putString(Constants.NAME, name)
                .putFloat(Constants.SALARY, salary)
                .putString(Constants.CURRENCY, currency)
                .apply()
    }
}
