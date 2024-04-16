package com.fiap.emotiondecoder

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity

class TelaResultado : ComponentActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_resultado)

        val buttonFinalizar = findViewById<Button>(R.id.buttonFinalizar)

        buttonFinalizar.setOnClickListener {

            val navegar = Intent(this, MainActivity::class.java)
            startActivity(navegar)
        }


    }
}