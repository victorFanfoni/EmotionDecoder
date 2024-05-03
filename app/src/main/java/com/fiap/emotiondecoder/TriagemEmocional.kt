package com.fiap.emotiondecoder

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.ComponentActivity

class TriagemEmocional : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tiagem_emocional)

        val buttonEnviar = findViewById<Button>(R.id.button_enviar)
        val radioFeliz = findViewById<RadioButton>(R.id.radio_alegria)
        val radioTriste = findViewById<RadioButton>(R.id.radio_tristeza)
        val radioRaiva = findViewById<RadioButton>(R.id.radio_raiva)
        val radioMedo = findViewById<RadioButton>(R.id.radio_medo)
        val comentario = findViewById<EditText>(R.id.edit_comentarios)

        buttonEnviar.setOnClickListener{
            val comentarios = comentario.text.toString()

            if (comentarios.isNotBlank() && (radioFeliz.isChecked || radioTriste.isChecked || radioRaiva.isChecked || radioMedo.isChecked)) {
                val emocaoSelecionada = when {
                    radioFeliz.isChecked -> "Feliz"
                    radioTriste.isChecked -> "Triste"
                    radioRaiva.isChecked -> "Raiva"
                    radioMedo.isChecked -> "Medo"
                    else -> ""
                }

                val relatorio = criarRelatorio(emocaoSelecionada, comentarios)
                val dicas = gerarDicas(emocaoSelecionada, comentarios)

                // Adicionando as dicas ao relatório
                val relatorioCompleto = "$relatorio\n\n$dicas"

                val intent = Intent(this, TelaResultado::class.java)
                intent.putExtra("relatorio", relatorioCompleto)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun criarRelatorio(emocao: String, comentarios: String): String {
        val relatorio = StringBuilder()

        relatorio.append("Emoção: $emocao\n")
        relatorio.append("Comentários: $comentarios\n")

        return relatorio.toString()
    }

    private fun gerarDicas(emocao: String, comentarios: String): String {
        val dicas = StringBuilder()

        when (emocao) {
            "Feliz" -> {
                dicas.append("Dicas para se sentir mais feliz:\n")
                dicas.append("- Continue fazendo o que te deixa feliz e aproveite os momentos positivos ao máximo.\n")
            }
            "Triste" -> {
                dicas.append("Dicas para lidar com a tristeza:\n")
                dicas.append("- Tente praticar atividades que te façam feliz e procure apoio de amigos e familiares.\n")
            }
            "Raiva" -> {
                dicas.append("Dicas para lidar com a raiva:\n")
                dicas.append("- Respire fundo e tente se acalmar antes de reagir. Procure resolver conflitos de maneira pacífica.\n")
            }
            "Medo" -> {
                dicas.append("Dicas para superar o medo:\n")
                dicas.append("- Identifique a causa do seu medo e procure enfrentá-lo aos poucos, buscando apoio se necessário.\n")
            }
        }

        if (comentarios.isNotBlank()) {
            dicas.append("\nDicas adicionais com base no seu comentário:\n")

            if (comentarios.contains("solidão", ignoreCase = true)) {
                dicas.append("- Se estiver se sentindo solitário, considere participar de atividades sociais ou conversar com alguém próximo.\n")
            }

            if (comentarios.contains("stress", ignoreCase = true)) {
                dicas.append("- Se estiver se sentindo estressado, tente praticar exercícios de relaxamento ou meditação para aliviar a tensão.\n")
            }
        }
        return dicas.toString()
    }
}
