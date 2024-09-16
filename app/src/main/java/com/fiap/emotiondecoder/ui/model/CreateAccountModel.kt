package com.fiap.emotiondecoder.ui.model

import android.util.Patterns

class CreateAccountModel {
    var email: String = ""
    var password: String = ""
    var confirmPassword: String = ""

    private fun isEmailValid(): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    private fun isPasswordValid(): Boolean {
        return password.length >= 6
    }
    private fun doPasswordsMatch(): Boolean {
        return password == confirmPassword
    }
    fun isValidInput(): Boolean {
        return email.isNotEmpty() &&
                password.isNotEmpty() &&
                confirmPassword.isNotEmpty() &&
                isEmailValid() &&
                isPasswordValid() &&
                doPasswordsMatch()
    }
}
