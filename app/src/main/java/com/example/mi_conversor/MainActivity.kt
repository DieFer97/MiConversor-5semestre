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
        Toast.makeText(this, "Navegando a Conversor de Temperatura", Toast.LENGTH_SHORT).show()
        // Intent(this, TemperatureActivity::class.java).also { startActivity(it) }
    }

    private fun navigateToCurrency() {
        Toast.makeText(this, "Navegando a Conversor de Monedas", Toast.LENGTH_SHORT).show()
        // Intent(this, CurrencyActivity::class.java).also { startActivity(it) }
    }

    private fun navigateToLength() {
        Toast.makeText(this, "Navegando a Conversor de Longitudes", Toast.LENGTH_SHORT).show()
        // Intent(this, LengthActivity::class.java).also { startActivity(it) }
    }

    private fun navigateToTime() {
        Toast.makeText(this, "Navegando a Conversor de Horas", Toast.LENGTH_SHORT).show()
        // Intent(this, TimeActivity::class.java).also { startActivity(it) }
    }
}