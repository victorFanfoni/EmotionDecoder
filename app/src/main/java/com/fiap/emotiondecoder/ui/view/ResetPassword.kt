package com.fiap.emotiondecoder.ui.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.fiap.emotiondecoder.R
import com.fiap.emotiondecoder.ui.controller.ResetPasswordController

class ResetPasswordActivity : ComponentActivity() {

    private lateinit var controller: ResetPasswordController

    private lateinit var emailEditText: EditText
    private lateinit var resetPasswordButton: Button
    private lateinit var voltarLoginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        emailEditText = findViewById(R.id.edit_email)
        resetPasswordButton = findViewById(R.id.buttonResetPassword)
        voltarLoginButton = findViewById(R.id.VoltaLogin)

        resetPasswordButton.setOnClickListener {
            val email = emailEditText.text.toString()
            if (email.isNotBlank()) {
                val resetPasswordController = ResetPasswordController(this)
                resetPasswordController.sendPasswordResetEmail(email)
            } else {
                Toast.makeText(
                    this,
                    "Por favor, insira um e-mail válido.",
                    Toast.LENGTH_LONG)
                    .show()
            }
        }
        voltarLoginButton.setOnClickListener {
            controller.navigateToLogin()
        }
    }
}
