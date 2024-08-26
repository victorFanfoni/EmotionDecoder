package com.fiap.emotiondecoder.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.fiap.emotiondecoder.R
import com.fiap.emotiondecoder.ui.controller.ResultScreenController

class ResultScreen : ComponentActivity() {

    private lateinit var controller: ResultScreenController
    private lateinit var textViewRelatorio: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_screen)

        // Inicializa o controller
        controller = ResultScreenController()

        // Inicializa as views
        textViewRelatorio = findViewById(R.id.textView)

        // Obtém os dados do Intent
        val relatorioCompleto = intent.getStringExtra(getString(R.string.relatorio)) ?: ""

        // Configura o modelo no controlador
        controller.setRelatorio(relatorioCompleto)

        // Exibe o relatório na tela
        textViewRelatorio.text = controller.formatarRelatorio()

        val buttonFinalizar = findViewById<Button>(R.id.buttonFinalizar)
        buttonFinalizar.setOnClickListener {
            controller.finalizarSessao(this)
        }

        val buttonVoltarTelaP = findViewById<Button>(R.id.buttonVoltarTelaP)
        buttonVoltarTelaP.setOnClickListener {
            controller.voltarParaTriagem(this)
        }
    }
}
