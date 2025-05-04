package com.example.imc

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val editPeso = findViewById<EditText>(R.id.editPeso)
        val editAltura = findViewById<EditText>(R.id.editAltura)
        val buttonCalcular = findViewById<Button>(R.id.buttonCalcular)
        val textResultado = findViewById<TextView>(R.id.textResultado)
        val textClassificacao = findViewById<TextView>(R.id.textClassificacao)

        buttonCalcular.setOnClickListener {
            val pesoTexto = editPeso.text.toString()
            val alturaTexto = editAltura.text.toString()

            if (pesoTexto.isNotEmpty() && alturaTexto.isNotEmpty()) {
                val peso = pesoTexto.toFloat()
                val alturaCm = alturaTexto.toFloat()
                val alturaM = alturaCm / 100

                val imc = peso / (alturaM * alturaM)
                val resultado = String.format("Seu IMC é: %.2f", imc)
                textResultado.text = resultado

                val classificacao = when {
                    imc < 18.5 -> "Abaixo do peso"
                    imc < 24.9 -> "Peso normal"
                    imc < 29.9 -> "Sobrepeso"
                    imc < 34.9 -> "Obesidade grau 1"
                    imc < 39.9 -> "Obesidade grau 2"
                    else -> "Obesidade grau 3"
                }

                textClassificacao.text = classificacao

                // Mudar a cor da classificação
                if (classificacao == "Peso normal") {
                    textClassificacao.setTextColor(Color.GREEN)
                } else {
                    textClassificacao.setTextColor(Color.RED)
                }

            } else {
                textResultado.text = "VOCÊ NÃO PREENCHEU O PESO E A ALTURA! :( "
                textClassificacao.text = ""
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
