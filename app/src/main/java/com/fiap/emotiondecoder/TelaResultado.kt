package com.fiap.emotiondecoder

import android.annotation.SuppressLint
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

        // Recebe o relatório enviado como extra
        val relatorio = intent.getStringExtra("relatorio")

        // Exibe o relatório no TextView
        textoResultado.text = relatorio

        buttonFinalizar.setOnClickListener {
            // Aqui você pode adicionar qualquer lógica adicional antes de finalizar a tela
            finish()
        }
    }
}
