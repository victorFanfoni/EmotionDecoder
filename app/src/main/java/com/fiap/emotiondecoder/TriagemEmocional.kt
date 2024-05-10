package com.fiap.emotiondecoder

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.ComponentActivity

class TriagemEmocional : ComponentActivity() {

    private lateinit var buttonEnviar: Button
    private lateinit var radioFeliz: RadioButton
    private lateinit var radioTriste: RadioButton
    private lateinit var radioRaiva: RadioButton
    private lateinit var radioMedo: RadioButton
    private lateinit var comentario: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tiagem_emocional)

        initializeViews()

        buttonEnviar.setOnClickListener {
            val comentarios = comentario.text.toString()

            if (isValidInput(comentarios)) {
                val emocaoSelecionada = getEmotionSelected()

                val relatorio = criarRelatorio(emocaoSelecionada, comentarios)
                val dicas = gerarDicas(emocaoSelecionada, comentarios)
                val relatorioCompleto = "$relatorio\n\n$dicas"

                startTelaResultado(relatorioCompleto)
            } else {
                displayToast(getString(R.string.preencha_Todos_campos))
            }
        }
    }

    private fun initializeViews() {
        buttonEnviar = findViewById(R.id.button_enviar)
        radioFeliz = findViewById(R.id.radio_alegria)
        radioTriste = findViewById(R.id.radio_tristeza)
        radioRaiva = findViewById(R.id.radio_raiva)
        radioMedo = findViewById(R.id.radio_medo)
        comentario = findViewById(R.id.edit_comentarios)
    }
    private fun isValidInput(comentarios: String): Boolean {
        return comentarios.isNotBlank() && (radioFeliz.isChecked || radioTriste.isChecked || radioRaiva.isChecked || radioMedo.isChecked)
    }
    private fun getEmotionSelected(): String {
        return when {
            radioFeliz.isChecked -> EMOTION_HAPPY
            radioTriste.isChecked -> EMOTION_SAD
            radioRaiva.isChecked -> EMOTION_ANGER
            radioMedo.isChecked -> EMOTION_FEAR
            else -> ""
        }
    }
    private fun criarRelatorio(emocao: String, comentarios: String): String {
        return "${getString(R.string.emocao)}: $emocao\n${getString(R.string.Comentarios)}: $comentarios"
    }

    private fun gerarDicas(emocao: String, comentarios: String): String {
        val dicas = StringBuilder()

        when (emocao) {
            EMOTION_HAPPY -> {
                dicas.append("${getString(R.string.Dicas_feliz)}\n")
                dicas.append("${getString(R.string.comentarioFeliz)}\n")
            }
            EMOTION_SAD -> {
                dicas.append("${getString(R.string.Dica_Triste)}\n")
                dicas.append("${getString(R.string.comentarioTriste)}\n")
            }
            EMOTION_ANGER -> {
                dicas.append("${getString(R.string.DicaRaiva)}\n")
                dicas.append("${getString(R.string.comentarioRaiva)}\n")
            }
            EMOTION_FEAR -> {
                dicas.append("${getString(R.string.DicaMedo)}\n")
                dicas.append("${getString(R.string.comentarioMedo)}\n")
            }
        }

        if (comentarios.isNotBlank()) {
            dicas.append("\n${getString(R.string.dica_base_comentario)}\n")

            if (comentarios.contains(getString(R.string.solidao), ignoreCase = true)) {
                dicas.append("${getString(R.string.dica)}\n")
            }

            if (comentarios.contains(getString(R.string.stress), ignoreCase = true)) {
                dicas.append("${getString(R.string.dicaStress)}\n")
            }
        }
        return dicas.toString()
    }
    private fun startTelaResultado(relatorioCompleto: String) {
        val intent = Intent(this, TelaResultado::class.java).apply {
            putExtra(getString(R.string.relatorio), relatorioCompleto)
        }
        startActivity(intent)
    }
    private fun displayToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    companion object {
        const val EMOTION_HAPPY = "Feliz"
        const val EMOTION_SAD = "Triste"
        const val EMOTION_ANGER = "Raiva"
        const val EMOTION_FEAR = "Medo"
    }
}
