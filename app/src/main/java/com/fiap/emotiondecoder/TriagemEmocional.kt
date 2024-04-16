package com.fiap.emotiondecoder

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity

class TriagemEmocional : ComponentActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tiagem_emocional)

        val buttonEnviar = findViewById<Button>(R.id.button_enviar)

        buttonEnviar.setOnClickListener {

            val navegar = Intent(this, TelaResultado::class.java)
            startActivity(navegar)
        }


    }
}