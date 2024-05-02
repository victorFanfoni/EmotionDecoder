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

                // Cria o relatório com base na emoção selecionada e nos comentários
                val relatorio = criarRelatorio(emocaoSelecionada, comentarios)

                // Envia o relatório como extra para a tela de resultado
                val intent = Intent(this, TelaResultado::class.java)
                intent.putExtra("relatorio", relatorio)
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

        // Adiciona dicas personalizadas com base no conteúdo do comentário
        relatorio.append(gerarDicas(comentarios))

        return relatorio.toString()
    }

    private fun gerarDicas(comentarios: String): String {
        val dicas = StringBuilder()

        if (comentarios.contains("triste", ignoreCase = true)) {
            dicas.append("Dica para tristeza: Tente praticar atividades que te façam feliz e procure apoio de amigos e familiares.\n")
        }

        if (comentarios.contains("raiva", ignoreCase = true)) {
            dicas.append("Dica para raiva: Respire fundo e tente se acalmar antes de reagir. Procure resolver conflitos de maneira pacífica.\n")
        }

        if (comentarios.contains("medo", ignoreCase = true)) {
            dicas.append("Dica para medo: Identifique a causa do seu medo e procure enfrentá-lo aos poucos, buscando apoio se necessário.\n")
        }

        if (comentarios.contains("feliz", ignoreCase = true)) {
            dicas.append("Dica para felicidade: Continue fazendo o que te deixa feliz e aproveite os momentos positivos ao máximo.\n")
        }

        return dicas.toString()
    }
}