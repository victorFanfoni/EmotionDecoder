package com.fiap.emotiondecoder.ui.controller

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.fiap.emotiondecoder.ui.model.CreateAccountModel
import com.fiap.emotiondecoder.ui.model.FirebaseAuthProvider
import com.fiap.emotiondecoder.ui.view.Login

class CreateAccountController(private val context: Context) {

    private val model = CreateAccountModel()
    private val authProvider = FirebaseAuthProvider()

    fun createAccount(email: String, password: String, confirmPassword: String) {
        model.email = email
        model.password = password
        model.confirmPassword = confirmPassword

        if (model.isValidInput()) {
            authProvider.auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = authProvider.auth.currentUser
                        user?.sendEmailVerification()?.addOnCompleteListener { verifyTask ->
                            if (verifyTask.isSuccessful) {
                                Toast.makeText(context, "E-mail de verificação enviado", Toast.LENGTH_LONG).show()
                            }
                        }
                        Toast.makeText(context, "Cadastro realizado com sucesso", Toast.LENGTH_LONG).show()
                        val intent = Intent(context, Login::class.java)
                        context.startActivity(intent)
                    } else {
                        Toast.makeText(context, "Erro ao cadastrar o usuário", Toast.LENGTH_LONG).show()
                    }
                }
        } else {
            val errorMessage = when {
                email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() -> "Preencha todos os campos"
                password != confirmPassword -> "As senhas não coincidem"
                else -> "Dados inválidos ou senhas não coincidem"
            }
            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
        }
    }
}
