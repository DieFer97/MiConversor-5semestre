package com.example.mi_conversor

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.mi_conversor.databinding.ActivityTemperatureBinding

class TemperatureActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTemperatureBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTemperatureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar spinners
        val units = arrayOf("Celsius", "Fahrenheit", "Kelvin")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, units)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.fromUnitSpinner.adapter = adapter
        binding.toUnitSpinner.adapter = adapter

        // Configurar botón de conversión
        binding.convertButton.setOnClickListener {
            convertTemperature()
        }

        // Configurar botón de volver
        binding.backButton.setOnClickListener {
            finish()
        }
    }

    private fun convertTemperature() {
        val input = binding.inputTemperature.text.toString().toDoubleOrNull()
        if (input == null) {
            Toast.makeText(this, "Por favor, ingrese una temperatura válida", Toast.LENGTH_SHORT).show()
            return
        }

        val fromUnit = binding.fromUnitSpinner.selectedItem.toString()
        val toUnit = binding.toUnitSpinner.selectedItem.toString()

        val result = when {
            fromUnit == toUnit -> input
            fromUnit == "Celsius" && toUnit == "Fahrenheit" -> (input * 9/5) + 32
            fromUnit == "Celsius" && toUnit == "Kelvin" -> input + 273.15
            fromUnit == "Fahrenheit" && toUnit == "Celsius" -> (input - 32) * 5/9
            fromUnit == "Fahrenheit" && toUnit == "Kelvin" -> (input - 32) * 5/9 + 273.15
            fromUnit == "Kelvin" && toUnit == "Celsius" -> input - 273.15
            fromUnit == "Kelvin" && toUnit == "Fahrenheit" -> (input - 273.15) * 9/5 + 32
            else -> throw IllegalArgumentException("Unidades no soportadas")
        }

        binding.resultTextView.text = "%.2f %s = %.2f %s".format(input, fromUnit, result, toUnit)
    }
}