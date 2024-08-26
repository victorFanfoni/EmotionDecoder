package com.fiap.emotiondecoder.ui.controller

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.fiap.emotiondecoder.ui.model.FirebaseAuthProvider
import com.fiap.emotiondecoder.ui.model.UserModel
import com.fiap.emotiondecoder.ui.view.EmotionalScreening

class LoginController(private val context: Context) {

    private val authProvider = FirebaseAuthProvider()

    fun signIn(userModel: UserModel) {
        if (userModel.isValid()) {
            authProvider.auth.signInWithEmailAndPassword(userModel.email, userModel.password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = authProvider.auth.currentUser
                        updateUI(user)
                        navigateToEmotionalScreening()
                    } else {
                        Toast.makeText(context, "Falha na autenticação.", Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                }
        } else {
            Toast.makeText(context, "Por favor, insira um email e senha válidos.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(user: Any?) {
        // Atualizar a interface do usuário aqui, se necessário
    }

    private fun navigateToEmotionalScreening() {
        val intent = Intent(context, EmotionalScreening::class.java)
        context.startActivity(intent)
    }
}
