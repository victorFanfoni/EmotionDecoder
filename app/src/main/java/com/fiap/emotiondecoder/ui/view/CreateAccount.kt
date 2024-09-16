package com.fiap.emotiondecoder.ui.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.fiap.emotiondecoder.R
import com.fiap.emotiondecoder.ui.controller.CreateAccountController

class CreateAccount : ComponentActivity() {

    private lateinit var controller: CreateAccountController

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        controller = CreateAccountController(this)

        val emailEditText = findViewById<EditText>(R.id.editTextEmail)
        val senhaEditText = findViewById<EditText>(R.id.editTextSenha)
        val confirmaSenhaEditText = findViewById<EditText>(R.id.editTextConfirmarSenha)
        val createAccountButton = findViewById<Button>(R.id.buttonCriarConta)
        val voltarLoginButton = findViewById<TextView>(R.id.VoltaLogin)

        createAccountButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val senha = senhaEditText.text.toString().trim()
            val confirmaSenha = confirmaSenhaEditText.text.toString().trim()
            controller.createAccount(email, senha, confirmaSenha)
        }

        voltarLoginButton.setOnClickListener {
            controller.navigateToLogin()
        }
    }
}
