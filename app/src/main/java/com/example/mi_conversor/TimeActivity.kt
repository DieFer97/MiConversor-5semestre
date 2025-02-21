package com.example.mi_conversor

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.mi_conversor.databinding.ActivityTimeBinding
import java.text.SimpleDateFormat
import java.util.*

class TimeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTimeBinding

    // Diferencia horaria respecto a UTC
    private val timeZones = mapOf(
        "Perú" to -5,
        "México" to -6,
        "Argentina" to -3,
        "España" to 1,
        "Japón" to 9
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val countries = timeZones.keys.toTypedArray()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, countries)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.fromCountrySpinner.adapter = adapter
        binding.toCountrySpinner.adapter = adapter

        binding.convertButton.setOnClickListener {
            convertTime()
        }

        binding.backButton.setOnClickListener {
            finish()
        }
    }

    private fun convertTime() {
        val inputTimeStr = binding.inputTime.text.toString()
        if (inputTimeStr.isEmpty() || !inputTimeStr.contains(":")) {
            Toast.makeText(this, "Ingrese una hora válida en formato HH:mm", Toast.LENGTH_SHORT).show()
            return
        }

        val fromCountry = binding.fromCountrySpinner.selectedItem.toString()
        val toCountry = binding.toCountrySpinner.selectedItem.toString()

        val fromOffset = timeZones[fromCountry] ?: return
        val toOffset = timeZones[toCountry] ?: return

        // Convertir la hora ingresada a minutos totales
        val parts = inputTimeStr.split(":")
        val hour = parts[0].toIntOrNull()
        val minute = parts[1].toIntOrNull()

        if (hour == null || minute == null || hour !in 0..23 || minute !in 0..59) {
            Toast.makeText(this, "Ingrese una hora válida en formato HH:mm", Toast.LENGTH_SHORT).show()
            return
        }

        // Calcular la nueva hora
        val totalMinutes = (hour * 60 + minute) + ((toOffset - fromOffset) * 60)
        val newHour = (totalMinutes / 60) % 24
        val newMinute = totalMinutes % 60

        val resultTime = String.format("%02d:%02d", newHour, newMinute)
        binding.resultTextView.text = "Hora en $toCountry: $resultTime"
    }
}
