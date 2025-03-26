package com.projetos.geradoraposta

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Random

class MainActivity : AppCompatActivity() {

    lateinit var prefers: SharedPreferences // criação de variável sem inicialização para receber os dados.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val receiverNumberGerator: TextView = findViewById(R.id.receiverNumberGerator)
        val receiverNumber: EditText = findViewById(R.id.receiceNumber)
        val btnGenerator: Button = findViewById(R.id.btnGerator)
        btnGenerator.setOnClickListener {
            geradorDeNumeros(receiverNumber.text.toString(), receiverNumberGerator)
        }

            // inicío do BD
            prefers = getSharedPreferences("db", MODE_PRIVATE) // variavel criada recebe o nome do arquivo e o modo de acesso.
            val numberOld = prefers.getString("number_old", null) // variavel criada para receber os dados do arquivo.
            if (numberOld != null) {
                receiverNumberGerator.text = "Aposta anterior: $numberOld"
            }
        }


    private fun geradorDeNumeros(number: String, receiverNumberGerator: TextView) {
        if (number.isEmpty() || number.toInt() !in 6..15) {
            Toast.makeText(this, "Informe um número entre 6 e 15", Toast.LENGTH_SHORT).show()
            return
        }

        val numbersAleatory = mutableSetOf<Int>()
        val random = Random()

        while (numbersAleatory.size < number.toInt()) {
            numbersAleatory.add(random.nextInt(60) + 1)
        }

        receiverNumberGerator.text = numbersAleatory.sorted().joinToString(" - ")


        // Gravar os dados no SharedPreferences
        val editor = prefers.edit() // variavel criada para editar o arquivo.
        editor.putString("number_old", receiverNumberGerator.text.toString()) // chave e valor.
        editor.apply() // salva os dados. Persistir os dadoså
    }

}