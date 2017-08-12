package com.knotworking.misr

import android.app.Activity
import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.knotworking.misr.databinding.ActivitySetupBinding

class SetupActivity : AppCompatActivity() {

    lateinit var binding: ActivitySetupBinding
    lateinit var adapter: ArrayAdapter<CharSequence>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_setup)

        adapter = ArrayAdapter.createFromResource(this, R.array.currency_array, android.R.layout.simple_spinner_item)

        binding.currencySpinner.adapter = adapter

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

        val sharedPreferences = getSharedPreferences(Constants.SHARED_PREFS, Context.MODE_PRIVATE)
        sharedPreferences.edit()
                .putString(Constants.NAME, name)
                .putFloat(Constants.SALARY, salary)
                .putString(Constants.CURRENCY, currency)
                .apply()
    }
}
