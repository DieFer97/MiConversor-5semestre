package com.example.mi_conversor

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.mi_conversor.databinding.ActivityCurrencyBinding

class CurrencyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCurrencyBinding

    private val exchangeRates = mapOf(
        "USD" to 1.0,
        "EUR" to 0.85,
        "GBP" to 0.75,
        "JPY" to 110.0,
        "MXN" to 20.0
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCurrencyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currencies = exchangeRates.keys.toTypedArray()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.fromCurrencySpinner.adapter = adapter
        binding.toCurrencySpinner.adapter = adapter

        binding.convertButton.setOnClickListener {
            convertCurrency()
        }

        binding.backButton.setOnClickListener {
            finish()
        }
    }

    private fun convertCurrency() {
        val amount = binding.inputAmount.text.toString().toDoubleOrNull()
        if (amount == null || amount <= 0) {
            Toast.makeText(this, "Ingrese una cantidad válida", Toast.LENGTH_SHORT).show()
            return
        }

        val fromCurrency = binding.fromCurrencySpinner.selectedItem.toString()
        val toCurrency = binding.toCurrencySpinner.selectedItem.toString()

        val fromRate = exchangeRates[fromCurrency] ?: return
        val toRate = exchangeRates[toCurrency] ?: return

        val convertedAmount = (amount / fromRate) * toRate

        binding.resultTextView.text = "%.2f %s = %.2f %s".format(amount, fromCurrency, convertedAmount, toCurrency)
    }
}
