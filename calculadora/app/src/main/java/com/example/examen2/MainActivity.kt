package com.example.examen2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.math.*

class MainActivity : AppCompatActivity() {

    private lateinit var editNumero1: EditText
    private lateinit var editNumero2: EditText
    private lateinit var textResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializa las vistas
        editNumero1 = findViewById(R.id.editNumero1)
        editNumero2 = findViewById(R.id.editNumero2)
        textResultado = findViewById(R.id.textResultado)

        // Configura los botones
        findViewById<Button>(R.id.buttonSuma).setOnClickListener { realizarOperacion("suma") }
        findViewById<Button>(R.id.buttonResta).setOnClickListener { realizarOperacion("resta") }
        findViewById<Button>(R.id.buttonMultiplicar).setOnClickListener { realizarOperacion("multiplicar") }
        findViewById<Button>(R.id.buttonDividir).setOnClickListener { realizarOperacion("dividir") }
        findViewById<Button>(R.id.buttonPotencia).setOnClickListener { realizarOperacion("potencia") }
        findViewById<Button>(R.id.buttonModulo).setOnClickListener { realizarOperacion("modulo") }
        findViewById<Button>(R.id.buttonPromedio).setOnClickListener { realizarOperacion("promedio") }
        findViewById<Button>(R.id.buttonMaximo).setOnClickListener { realizarOperacion("maximo") }
        findViewById<Button>(R.id.buttonMinimo).setOnClickListener { realizarOperacion("minimo") }
        findViewById<Button>(R.id.buttonDiferenciaAbsoluta).setOnClickListener { realizarOperacion("diferencia_absoluta") }
    }

    private fun realizarOperacion(operacion: String) {
        // Obtiene los valores de los EditText
        val numero1 = editNumero1.text.toString().toDoubleOrNull() ?: return mostrarError()
        val numero2 = editNumero2.text.toString().toDoubleOrNull() ?: return mostrarError()

        val resultado = when (operacion) {
            "suma" -> numero1 + numero2
            "resta" -> numero1 - numero2
            "multiplicar" -> numero1 * numero2
            "dividir" -> if (numero2 != 0.0) numero1 / numero2 else Double.NaN
            "potencia" -> Math.pow(numero1, numero2)
            "modulo" -> numero1 % numero2
            "promedio" -> (numero1 + numero2) / 2
            "maximo" -> max(numero1, numero2)
            "minimo" -> min(numero1, numero2)
            "diferencia_absoluta" -> abs(numero1 - numero2)
            else -> Double.NaN
        }

        // Muestra el resultado
        textResultado.text = if (resultado.isNaN()) {
            "Error en la operación"
        } else {
            "Resultado: $resultado"
        }
    }

    private fun mostrarError() {
        textResultado.text = "Por favor, ingrese números válidos"
    }
}
