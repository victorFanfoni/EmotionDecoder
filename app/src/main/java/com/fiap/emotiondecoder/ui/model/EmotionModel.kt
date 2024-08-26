package com.fiap.emotiondecoder.ui.model

import android.content.Context
import com.fiap.emotiondecoder.R

class EmotionModel {

    companion object {
        const val EMOTION_HAPPY = "Feliz"
        const val EMOTION_SAD = "Triste"
        const val EMOTION_ANGER = "Raiva"
        const val EMOTION_FEAR = "Medo"
    }

    fun criarRelatorio(emocao: String, comentarios: String, context: Context): String {
        return "${context.getString(R.string.emocao)}: $emocao\n${context.getString(R.string.Comentarios)}: $comentarios"
    }

    fun gerarDicas(emocao: String, comentarios: String, context: Context): String {
        val dicas = StringBuilder()

        when (emocao) {
            EMOTION_HAPPY -> {
                dicas.append("${context.getString(R.string.Dicas_feliz)}\n")
                dicas.append("${context.getString(R.string.comentarioFeliz)}\n")
            }
            EMOTION_SAD -> {
                dicas.append("${context.getString(R.string.Dica_Triste)}\n")
                dicas.append("${context.getString(R.string.comentarioTriste)}\n")
            }
            EMOTION_ANGER -> {
                dicas.append("${context.getString(R.string.DicaRaiva)}\n")
                dicas.append("${context.getString(R.string.comentarioRaiva)}\n")
            }
            EMOTION_FEAR -> {
                dicas.append("${context.getString(R.string.DicaMedo)}\n")
                dicas.append("${context.getString(R.string.comentarioMedo)}\n")
            }
        }

        if (comentarios.isNotBlank()) {
            dicas.append("\n${context.getString(R.string.dica_base_comentario)}\n")

            if (comentarios.contains(context.getString(R.string.solidao), ignoreCase = true)) {
                dicas.append("${context.getString(R.string.dica)}\n")
            }

            if (comentarios.contains(context.getString(R.string.stress), ignoreCase = true)) {
                dicas.append("${context.getString(R.string.dicaStress)}\n")
            }
        }
        return dicas.toString()
    }
}
