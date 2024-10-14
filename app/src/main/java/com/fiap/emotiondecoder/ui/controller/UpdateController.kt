package com.fiap.emotiondecoder.ui.controller

import android.content.Context
import android.text.TextUtils
import android.widget.Toast
import com.fiap.emotiondecoder.ui.model.FirebaseAuthProvider
import com.google.firebase.auth.FirebaseAuthInvalidUserException

class UpdateController(private val context: Context) {
    val authProvider = FirebaseAuthProvider()

    fun updateUser(newEmail: String, newPassword: String, confirmPassword: String) {
        if (TextUtils.isEmpty(newEmail) || TextUtils.isEmpty(newPassword) || TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(context, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
            return
        }

        if (newPassword != confirmPassword) {
            Toast.makeText(context, "As senhas não coincidem.", Toast.LENGTH_SHORT).show()
            return
        }

        val currentUser = authProvider.auth.currentUser
        currentUser?.let {
            it.updateEmail(newEmail).addOnCompleteListener { emailTask ->
                if (emailTask.isSuccessful) {
                    // Atualizar senha após e-mail ser atualizado
                    it.updatePassword(newPassword).addOnCompleteListener { passwordTask ->
                        if (passwordTask.isSuccessful) {
                            Toast.makeText(context, "Dados atualizados com sucesso.", Toast.LENGTH_SHORT).show()
                        } else {
                            handleAuthError(passwordTask.exception)
                        }
                    }
                } else {
                    handleAuthError(emailTask.exception)
                }
            }
        } ?: run {
            Toast.makeText(context, "Usuário não autenticado.", Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteUser() {
        val currentUser = authProvider.auth.currentUser
        currentUser?.let {
            it.delete().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "Conta deletada com sucesso.", Toast.LENGTH_SHORT).show()
                } else {
                    handleAuthError(task.exception)
                }
            }
        } ?: run {
            Toast.makeText(context, "Usuário não autenticado.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleAuthError(exception: Exception?) {
        when (exception) {
            is FirebaseAuthInvalidUserException -> {
                Toast.makeText(context, "Usuário inválido.", Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(context, "Erro: ${exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
