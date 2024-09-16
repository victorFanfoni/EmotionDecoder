package com.fiap.emotiondecoder.ui.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import androidx.activity.ComponentActivity
import com.fiap.emotiondecoder.R
import com.fiap.emotiondecoder.ui.controller.EmotionalScreeningController
import com.fiap.emotiondecoder.ui.model.EmotionModel

class EmotionalScreening : ComponentActivity() {

    private lateinit var controller: EmotionalScreeningController
    private lateinit var radioAlegria: RadioButton
    private lateinit var radioTristeza: RadioButton
    private lateinit var radioRaiva: RadioButton
    private lateinit var radioMedo: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emotionalscreening)

        controller = EmotionalScreeningController(this)

        val buttonEnviar = findViewById<Button>(R.id.button_enviar)
        val editComentarios = findViewById<EditText>(R.id.edit_comentarios)
        radioAlegria = findViewById(R.id.radio_alegria)
        radioTristeza = findViewById(R.id.radio_tristeza)
        radioRaiva = findViewById(R.id.radio_raiva)
        radioMedo = findViewById(R.id.radio_medo)

        buttonEnviar.setOnClickListener {
            val selectedEmotion = getSelectedEmotion()
            val comments = editComentarios.text.toString()
            controller.handleEmotionSelection(selectedEmotion, comments)
        }
    }
    private fun getSelectedEmotion(): String {
        return when {
            radioAlegria.isChecked -> EmotionModel.EMOTION_HAPPY
            radioTristeza.isChecked -> EmotionModel.EMOTION_SAD
            radioRaiva.isChecked -> EmotionModel.EMOTION_ANGER
            radioMedo.isChecked -> EmotionModel.EMOTION_FEAR
            else -> ""
        }
    }
}
