package com.example.mi_conversor

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.mi_conversor.databinding.ActivityTimeBinding

class TimeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTimeBinding

    private val timeUnits = mapOf(
        "Segundos" to 1.0,
        "Minutos" to 1.0 / 60,
        "Horas" to 1.0 / 3600,
        "Días" to 1.0 / 86400,
        "Semanas" to 1.0 / 604800
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val units = timeUnits.keys.toTypedArray()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, units)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.fromTimeSpinner.adapter = adapter
        binding.toTimeSpinner.adapter = adapter

        binding.convertButton.setOnClickListener {
            convertTime()
        }

        binding.backButton.setOnClickListener {
            finish()
        }
    }

    private fun convertTime() {
        val amount = binding.inputTime.text.toString().toDoubleOrNull()
        if (amount == null || amount <= 0) {
            Toast.makeText(this, "Ingrese un valor válido", Toast.LENGTH_SHORT).show()
            return
        }

        val fromUnit = binding.fromTimeSpinner.selectedItem.toString()
        val toUnit = binding.toTimeSpinner.selectedItem.toString()

        val fromRate = timeUnits[fromUnit] ?: return
        val toRate = timeUnits[toUnit] ?: return

        val convertedAmount = (amount / fromRate) * toRate

        binding.resultTextView.text = "%.2f %s = %.2f %s".format(amount, fromUnit, convertedAmount, toUnit)
    }
}
