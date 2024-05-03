package com.fiap.emotiondecoder

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity

class TelaResultado : ComponentActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_resultado)

        val buttonFinalizar = findViewById<Button>(R.id.buttonFinalizar)
        val textoResultado = findViewById<TextView>(R.id.textView)
        val buttonVoltar = findViewById<Button>(R.id.buttonVoltarTelaP)

        val relatorio = intent.getStringExtra("relatorio")

        textoResultado.text = relatorio

        buttonFinalizar.setOnClickListener {
            navigateToNextScreen()
        }

        buttonVoltar.setOnClickListener {
            val intent = Intent(this, TriagemEmocional::class.java)
            startActivity(intent)
        }

    }
    private fun navigateToNextScreen() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
