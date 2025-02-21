package com.example.mi_conversor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var temperatureButton: Button
    private lateinit var currencyButton: Button
    private lateinit var lengthButton: Button
    private lateinit var timeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar los botones
        temperatureButton = findViewById(R.id.temperatureButton)
        currencyButton = findViewById(R.id.currencyButton)
        lengthButton = findViewById(R.id.lengthButton)
        timeButton = findViewById(R.id.timeButton)

        // Configurar los listeners de clic
        temperatureButton.setOnClickListener { navigateToTemperature() }
        currencyButton.setOnClickListener { navigateToCurrency() }
        lengthButton.setOnClickListener { navigateToLength() }
        timeButton.setOnClickListener { navigateToTime() }
    }

    private fun navigateToTemperature() {
        // Por ahora, solo mostraremos un mensaje. Más adelante, navegaremos a la actividad correspondiente.
        Intent(this, TemperatureActivity::class.java).also { startActivity(it) }
        // Intent(this, TemperatureActivity::class.java).also { startActivity(it) }
    }

    private fun navigateToCurrency() {
        Intent(this, CurrencyActivity::class.java).also { startActivity(it) }
        // Intent(this, CurrencyActivity::class.java).also { startActivity(it) }
    }

    private fun navigateToLength() {
        Intent(this, LengthActivity::class.java).also { startActivity(it) }
        // Intent(this, LengthActivity::class.java).also { startActivity(it) }
    }

    private fun navigateToTime() {
        Intent(this, TimeActivity::class.java).also { startActivity(it) }
        // Intent(this, TimeActivity::class.java).also { startActivity(it) }
    }
}