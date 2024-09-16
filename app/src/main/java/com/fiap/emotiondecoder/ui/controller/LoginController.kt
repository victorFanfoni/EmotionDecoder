package com.fiap.emotiondecoder.ui.controller

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.fiap.emotiondecoder.ui.model.FirebaseAuthProvider
import com.fiap.emotiondecoder.ui.model.UserModel
import com.fiap.emotiondecoder.ui.view.CreateAccount
import com.fiap.emotiondecoder.ui.view.EmotionalScreening
import com.fiap.emotiondecoder.ui.view.Login
import com.fiap.emotiondecoder.ui.view.ResetPasswordActivity

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
                    }
                    else {
                        Toast.makeText(
                            context,
                            "Falha na autenticação.",
                            Toast.LENGTH_SHORT)
                            .show()
                        updateUI(null)
                    }
                }
        } else {
            Toast.makeText(
                context,
                "Por favor, insira um email e senha válidos.",
                Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun updateUI(user: Any?) {
    }

    private fun navigateToEmotionalScreening() {
        val intent = Intent(context, EmotionalScreening::class.java)
        context.startActivity(intent)
    }

    fun navigateToCreateAccount() {
        val intent = Intent(context, CreateAccount::class.java)
        context.startActivity(intent)
        (context as? Login)?.finish()
    }

    fun navigateToResetPassword() {
        val intent = Intent(context, ResetPasswordActivity::class.java)
        context.startActivity(intent)
        (context as? Login)?.finish()
    }
}
