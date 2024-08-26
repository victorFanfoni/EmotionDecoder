package com.fiap.emotiondecoder.ui.controller

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.fiap.emotiondecoder.R
import com.fiap.emotiondecoder.ui.model.EmotionModel
import com.fiap.emotiondecoder.ui.view.ResultScreen

class EmotionalScreeningController(private val context: Context) {

    private val model = EmotionModel()

    fun handleEmotionSelection(emotion: String, comments: String) {
        if (isValidInput(emotion, comments)) {
            val report = model.criarRelatorio(emotion, comments, context)
            val tips = model.gerarDicas(emotion, comments, context)
            val completeReport = "$report\n\n$tips"

            startResultScreen(completeReport)
        } else {
            displayToast("Preencha todos os campos")
        }
    }

    private fun isValidInput(emotion: String, comments: String): Boolean {
        return emotion.isNotBlank() && comments.isNotBlank()
    }

    private fun startResultScreen(report: String) {
        val intent = Intent(context, ResultScreen::class.java).apply {
            putExtra(context.getString(R.string.relatorio), report)
        }
        context.startActivity(intent)
    }

    private fun displayToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
