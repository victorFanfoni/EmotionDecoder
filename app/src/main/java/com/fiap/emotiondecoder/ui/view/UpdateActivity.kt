package com.fiap.emotiondecoder.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import com.fiap.emotiondecoder.Home
import com.fiap.emotiondecoder.R
import com.fiap.emotiondecoder.ui.controller.UpdateController

class UpdateActivity : ComponentActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var deleteButton: Button
    private lateinit var voltar: Button
    private lateinit var updateController: UpdateController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        emailEditText = findViewById(R.id.edit_email)
        passwordEditText = findViewById(R.id.edit_senha)
        confirmPasswordEditText = findViewById(R.id.edit_confirm_senha)
        saveButton = findViewById(R.id.button)
        deleteButton = findViewById(R.id.button2)
        voltar = findViewById(R.id.button3)

        updateController = UpdateController(this)

        val currentUser = updateController.authProvider.auth.currentUser
        currentUser?.let {
            emailEditText.setText(it.email)
        }

        saveButton.setOnClickListener {
            val newEmail = emailEditText.text.toString().trim()
            val newPassword = passwordEditText.text.toString().trim()
            val confirmPassword = confirmPasswordEditText.text.toString().trim()
            updateController.updateUser(newEmail, newPassword, confirmPassword)
        }

        deleteButton.setOnClickListener {
            updateController.deleteUser()
            startActivity(Intent(this, Login::class.java))
            finish()
        }

        voltar.setOnClickListener {
            startActivity(Intent(this, Home::class.java))
            finish()
        }
    }
}
