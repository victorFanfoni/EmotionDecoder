package com.fiap.emotiondecoder.ui.controller

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.fiap.emotiondecoder.ui.view.Login
import com.fiap.emotiondecoder.ui.view.ResetPasswordActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class ResetPasswordController(private val context: Context) {

    fun sendPasswordResetEmail(emailAddress: String) {
        if (emailAddress.isNotBlank()) {  // Use isNotBlank() to also check for whitespace
            Firebase.auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            context,
                            "E-mail de redefinição de senha enviado.",
                            Toast.LENGTH_LONG)
                            .show()
                    } else {
                        Toast.makeText(
                            context,
                            "Erro ao enviar o e-mail de redefinição.",
                            Toast.LENGTH_LONG)
                            .show()
                    }
                }
        } else {
            Toast.makeText(
                context,
                "Por favor, insira um e-mail válido.",
                Toast.LENGTH_LONG)
                .show()
        }
    }

    fun navigateToLogin() {
        val intent = Intent(context, Login::class.java)
        context.startActivity(intent)
        (context as? ResetPasswordActivity)?.finish()
    }
}
