package com.example.examen2

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var editNombre: EditText
    private lateinit var editChar1: EditText
    private lateinit var editChar2: EditText
    private lateinit var editChar3: EditText
    private lateinit var editChar4: EditText
    private lateinit var editChar5: EditText
    private lateinit var textAciertos: TextView
    private lateinit var buttonComenzar: Button
    private lateinit var buttonValidar: Button

    private val palabras = listOf("array", "clase", "login", "objeto", "input", "debug", "patch", "query", "stack", "cache")
    private lateinit var palabraAleatoria: String
    private var intentosRestantes = 5
    private var aciertos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editNombre = findViewById(R.id.editNombre)
        editChar1 = findViewById(R.id.editChar1)
        editChar2 = findViewById(R.id.editChar2)
        editChar3 = findViewById(R.id.editChar3)
        editChar4 = findViewById(R.id.editChar4)
        editChar5 = findViewById(R.id.editChar5)
        textAciertos = findViewById(R.id.textAciertos)
        buttonComenzar = findViewById(R.id.buttonComenzar)
        buttonValidar = findViewById(R.id.buttonValidar)

        buttonComenzar.setOnClickListener(this)
        buttonValidar.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonComenzar -> {
                val nombre = editNombre.text.toString()
                if (nombre.isEmpty()) {
                    Toast.makeText(this, "Faltan datos", Toast.LENGTH_SHORT).show()
                } else {
                    palabraAleatoria = palabras[Random.nextInt(palabras.size)]
                    aciertos = 0
                    intentosRestantes = 5
                    textAciertos.text = "Aciertos: 0"
                    Toast.makeText(this, "¡Hola $nombre! ¡Comienza a jugar!", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.buttonValidar -> {
                validarIntento()
            }
        }
    }

    private fun validarIntento() {
        val letrasIntroducidas = listOf(
            editChar1.text.toString(),
            editChar2.text.toString(),
            editChar3.text.toString(),
            editChar4.text.toString(),
            editChar5.text.toString()
        )

        if (letrasIntroducidas.any { it.isEmpty() }) {
            Toast.makeText(this, "Completa todas las letras", Toast.LENGTH_SHORT).show()
            return
        }

        val palabraArray = palabraAleatoria.toCharArray()
        val letrasRestantes = palabraAleatoria.toMutableList()
        aciertos = 0

        // Paso 1: Verificar letras correctas (verde)
        for (i in letrasIntroducidas.indices) {
            if (letrasIntroducidas[i] == palabraArray[i].toString()) {
                setBackground(i, Color.GREEN)
                aciertos++
                letrasRestantes[i] = ' '  // Marcar la letra como usada
            }
        }

        // Paso 2: Verificar letras en la palabra pero en la posición incorrecta (amarillo)
        for (i in letrasIntroducidas.indices) {
            if (letrasIntroducidas[i] != palabraArray[i].toString() && letrasRestantes.contains(letrasIntroducidas[i][0])) {
                setBackground(i, Color.YELLOW)
                letrasRestantes[letrasRestantes.indexOf(letrasIntroducidas[i][0])] = ' '  // Marcar la letra como usada
            } else if (letrasIntroducidas[i] != palabraArray[i].toString()) {
                setBackground(i, Color.WHITE)
            }
        }

        textAciertos.text = "Aciertos: $aciertos"
        intentosRestantes--

        if (aciertos == 5) {
            Toast.makeText(this, "Enhorabuena ${editNombre.text}, has acertado la palabra", Toast.LENGTH_LONG).show()
        } else if (intentosRestantes == 0) {
            Toast.makeText(this, "Fin del juego. La palabra era: $palabraAleatoria", Toast.LENGTH_LONG).show()
        }
    }

    private fun setBackground(index: Int, color: Int) {
        when (index) {
            0 -> editChar1.setBackgroundColor(color)
            1 -> editChar2.setBackgroundColor(color)
            2 -> editChar3.setBackgroundColor(color)
            3 -> editChar4.setBackgroundColor(color)
            4 -> editChar5.setBackgroundColor(color)
        }
    }
}
