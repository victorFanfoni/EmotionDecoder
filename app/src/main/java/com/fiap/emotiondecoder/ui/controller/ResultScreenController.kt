package com.fiap.emotiondecoder.ui.controller

import android.content.Context
import android.content.Intent
import com.fiap.emotiondecoder.ui.model.ResultScreenModel
import com.fiap.emotiondecoder.ui.view.EmotionalScreening
import com.fiap.emotiondecoder.ui.view.Login
import com.google.firebase.auth.FirebaseAuth

class ResultScreenController {

    private val model = ResultScreenModel()

    fun setRelatorio(relatorio: String) {
        model.relatorio = relatorio
    }

    fun finalizarSessao(context: Context) {
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(context, Login::class.java)
        context.startActivity(intent)
        if (context is EmotionalScreening) {
            context.finish()
        }
    }

    fun voltarParaTriagem(context: Context) {
        val intent = Intent(context, EmotionalScreening::class.java)
        context.startActivity(intent)
    }

    fun formatarRelatorio(): String {
        // Aqui você pode adicionar qualquer lógica adicional para formatar o relatório antes de exibi-lo
        return model.relatorio
    }
}
