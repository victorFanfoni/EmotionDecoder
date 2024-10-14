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
    private lateinit var updateController: UpdateController
    private lateinit var voltar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        // Inicializar views
        emailEditText = findViewById(R.id.editTextTextEmailAddress)
        passwordEditText = findViewById(R.id.editTextTextPassword)
        confirmPasswordEditText = findViewById(R.id.editTextTextPassword2)
        saveButton = findViewById(R.id.button)
        deleteButton = findViewById(R.id.button2)
        voltar = findViewById(R.id.button3)

        // Inicializar o controlador
        updateController = UpdateController(this)

        // Ler os dados atuais do usuário
        val currentUser = updateController.authProvider.auth.currentUser
        currentUser?.let {
            emailEditText.setText(it.email)
        }

        // Configurar botão de salvar
        saveButton.setOnClickListener {
            val newEmail = emailEditText.text.toString().trim()
            val newPassword = passwordEditText.text.toString().trim()
            val confirmPassword = confirmPasswordEditText.text.toString().trim()
            updateController.updateUser(newEmail, newPassword, confirmPassword)
        }

        // Configurar botão de deletar
        deleteButton.setOnClickListener {
            updateController.deleteUser()
            // Redirecionar para a tela de login, caso a conta seja deletada
            startActivity(Intent(this, Login::class.java))
            finish()
        }

        voltar.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
            finish()
        }

    }
}
