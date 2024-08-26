package com.fiap.emotiondecoder.ui.model

import android.util.Patterns

class CreateAccountModel {
    var email: String = ""
    var password: String = ""
    var confirmPassword: String = ""

    // Função para validar o email
    private fun isEmailValid(): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // Função para validar a senha (exemplo básico)
    private fun isPasswordValid(): Boolean {
        return password.length >= 6 // Você pode adicionar mais critérios de validação aqui
    }

    // Função para verificar se as senhas coincidem
    private fun doPasswordsMatch(): Boolean {
        return password == confirmPassword
    }

    // Função para validar todas as entradas
    fun isValidInput(): Boolean {
        return email.isNotEmpty() &&
                password.isNotEmpty() &&
                confirmPassword.isNotEmpty() &&
                isEmailValid() &&
                isPasswordValid() &&
                doPasswordsMatch()
    }
}
