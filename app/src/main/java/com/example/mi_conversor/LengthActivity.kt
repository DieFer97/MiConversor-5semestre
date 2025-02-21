package com.example.mi_conversor

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.mi_conversor.databinding.ActivityLengthBinding

class LengthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLengthBinding

    private val lengthUnits = mapOf(
        "Metros" to 1.0,
        "Kilómetros" to 0.001,
        "Centímetros" to 100.0,
        "Milímetros" to 1000.0,
        "Pulgadas" to 39.3701,
        "Pies" to 3.28084,
        "Millas" to 0.000621371
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLengthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val units = lengthUnits.keys.toTypedArray()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, units)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.fromLengthSpinner.adapter = adapter
        binding.toLengthSpinner.adapter = adapter

        binding.convertButton.setOnClickListener {
            convertLength()
        }

        binding.backButton.setOnClickListener {
            finish()
        }
    }

    private fun convertLength() {
        val amount = binding.inputLength.text.toString().toDoubleOrNull()
        if (amount == null || amount <= 0) {
            Toast.makeText(this, "Ingrese una cantidad válida", Toast.LENGTH_SHORT).show()
            return
        }

        val fromUnit = binding.fromLengthSpinner.selectedItem.toString()
        val toUnit = binding.toLengthSpinner.selectedItem.toString()

        val fromRate = lengthUnits[fromUnit] ?: return
        val toRate = lengthUnits[toUnit] ?: return

        val convertedAmount = (amount / fromRate) * toRate

        binding.resultTextView.text = "%.2f %s = %.2f %s".format(amount, fromUnit, convertedAmount, toUnit)
    }
}
