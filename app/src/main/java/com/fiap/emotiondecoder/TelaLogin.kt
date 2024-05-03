package com.fiap.emotiondecoder

import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.fiap.emotiondecoder.databinding.TelaDeLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException

class TelaLogin : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    private var binding: TelaDeLoginBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TelaDeLoginBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        auth = FirebaseAuth.getInstance()

        binding?.buttonCriarConta?.setOnClickListener{
            startActivity(Intent(this, CriacaoConta::class.java))
        }

        binding?.buttonEntrar?.setOnClickListener {
            val email = binding?.editEmail?.text.toString()
            val senha = binding?.editSenha?.text.toString()

            when {
                email.isEmpty() -> binding?.editEmail?.error = "Preencha o E-mail!"
                senha.isEmpty() -> binding?.editSenha?.error = "Preencha a senha!"
                !email.contains("@") -> mostrarSnackbar("E-mail inválido!")
                else -> signInWithEmailAndPassword(email, senha)
            }
        }
    }

    private fun signInWithEmailAndPassword(email: String, password: String) {
        if (!isNetworkAvailable()) {
            mostrarSnackbar("Sem conexão com a internet")
            return
        }

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d(TAG, "signInWithEmailAndPassword: Sucesso")
                navigateToNextScreen()
            } else {
                Log.e(TAG, "signInWithEmailAndPassword: Falha", task.exception)
                val errorMessage = when (task.exception) {
                    is FirebaseAuthInvalidUserException -> "Usuário não encontrado"
                    is FirebaseAuthInvalidCredentialsException -> "Credenciais inválidas"
                    else -> "Erro ao autenticar: ${task.exception?.message}"
                }
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateToNextScreen() {
        startActivity(Intent(this, TriagemEmocional::class.java))
        finish()
    }

    private fun mostrarSnackbar(mensagem: String) {
        Snackbar.make(binding?.root ?: return, mensagem, Snackbar.LENGTH_SHORT).show()
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as? ConnectivityManager
        return connectivityManager?.activeNetworkInfo?.isConnected ?: false
    }

    companion object {
        private const val TAG = "EmailAndPassword"
    }
}
