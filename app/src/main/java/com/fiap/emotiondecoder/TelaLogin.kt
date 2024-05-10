package com.fiap.emotiondecoder

import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.fiap.emotiondecoder.databinding.TelaDeLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class TelaLogin : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    private var binding: TelaDeLoginBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TelaDeLoginBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        auth = FirebaseAuth.getInstance()
        val redefinirSenha = findViewById<TextView>(R.id.textView4)

        binding?.buttonCriarConta?.setOnClickListener{
            val intent = Intent(this, CriacaoConta::class.java)
            startActivity(intent)
            finish()
        }

        binding?.buttonEntrar?.setOnClickListener {


            val email = binding?.editEmail?.text.toString()
            val senha = binding?.editSenha?.text.toString()

            if (email.isEmpty() || senha.isEmpty()){
                binding?.editEmail?.error = getString(R.string.email)
                binding?.editSenha?.error = getString(R.string.senhaAlert)
            }else if (!email.contains("@")){
                mostrarSnackbar(getString(R.string.Emailerr))
            }else{
                signInWithEmailAndPassword(email, senha)
            }
        }

        redefinirSenha.setOnClickListener{
            val intent = Intent(this, PasswordResetActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun signInWithEmailAndPassword(email: String, password: String) {
        if (!isNetworkAvailable()) {
            mostrarSnackbar(getString(R.string.net))
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    startActivity(Intent(this, TriagemEmocional::class.java))
                    finish()
                } else {
                    Toast.makeText(
                        this,
                        getString(R.string.errLog),
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
    }
    private fun mostrarSnackbar(mensagem: String) {
        Snackbar.make(binding?.root ?: return, mensagem, Snackbar.LENGTH_SHORT).show()
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as? ConnectivityManager
        return connectivityManager?.activeNetworkInfo?.isConnected?: false
    }

    companion object {
        private const val TAG = "EmailAndPassword"
    }
}
