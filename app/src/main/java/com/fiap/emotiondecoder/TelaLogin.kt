package com.fiap.emotiondecoder

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.fiap.emotiondecoder.databinding.TelaDeLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
class TelaLogin : ComponentActivity() {

    private lateinit var auth: FirebaseAuth
    private var binding: TelaDeLoginBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TelaDeLoginBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        auth = Firebase.auth

        binding?.buttonCriarConta?.setOnClickListener{
            val intent = Intent(this, CriacaoConta::class.java)
            startActivity(intent)
        }

        binding?.buttonEntrar?.setOnClickListener {
            val email: String = binding?.editEmail?.text.toString()
            val senha: String = binding?.editSenha?.text.toString()

            if (email.isEmpty() || senha.isEmpty()) {
                Toast.makeText(baseContext, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            } else {
                signInWithEmailAndPassword(email, senha)
            }
        }
    }

    private fun signInWithEmailAndPassword(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful){
                Log.d(TAG, "signInWithEmailAndPassword: Success")
                // Navigate to the next screen or perform other actions upon successful login.
                navigateToNextScreen()
            } else {
                Log.d(TAG, "signInWithEmailAndPassword: Failure", task.exception)
                // Display a more specific error message based on the task's exception.
                val errorMessage = task.exception?.message ?: "Authentication failed"
                Toast.makeText(baseContext, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateToNextScreen() {
        // Example: Navigate to the home screen upon successful login.
        val intent = Intent(this, TriagemEmocional::class.java)
        startActivity(intent)
        finish() // Finish the current activity to prevent the user from navigating back to it.
    }

    companion object {
        private const val TAG = "EmailAndPassword"
    }
}
