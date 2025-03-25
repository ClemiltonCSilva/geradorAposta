package com.projetos.geradoraposta

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val receiverNumber: EditText = findViewById(R.id.receiceNumber)
        val btnGenerator: Button = findViewById(R.id.btnGerator)

        btnGenerator.setOnClickListener {
        geradorDeNumeros(receiverNumber.text.toString())
        }

    }

    private fun geradorDeNumeros(number: String) {
        if (number.isEmpty() || number.toInt() !in 6..15) {
            Toast.makeText(this, "Informe um número entre 6 e 15", Toast.LENGTH_SHORT).show()
            return
        }

        val numbersAleatory = mutableSetOf<Int>()
        val random = Random()

        while (numbersAleatory.size < number.toInt()){
            numbersAleatory.add(random.nextInt(60)+1)
        }

        val receiverNumberGerator: TextView = findViewById(R.id.receiverNumberGerator)
        receiverNumberGerator.text = numbersAleatory.sorted().joinToString(" - ")
    }



}