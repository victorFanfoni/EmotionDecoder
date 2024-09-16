package com.fiap.emotiondecoder.ui.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.fiap.emotiondecoder.R
import com.fiap.emotiondecoder.ui.controller.LoginController
import com.fiap.emotiondecoder.ui.model.UserModel

class Login : ComponentActivity() {

    private lateinit var controller: LoginController

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        controller = LoginController(this)

        val buttonLogin = findViewById<Button>(R.id.button_entrar)
        val editEmail = findViewById<EditText>(R.id.edit_email)
        val editPassword = findViewById<EditText>(R.id.edit_senha)
        val buttonCreateAccount = findViewById<TextView>(R.id.button_criar_conta)
        val buttonResetPassword = findViewById<TextView>(R.id.textView4)


        buttonLogin.setOnClickListener {
            val email = editEmail.text.toString()
            val password = editPassword.text.toString()
            val userModel = UserModel(email, password)
            controller.signIn(userModel)
        }
        buttonResetPassword.setOnClickListener {
            controller.navigateToResetPassword()
        }

        buttonCreateAccount.setOnClickListener {
            controller.navigateToCreateAccount()
        }
    }
}