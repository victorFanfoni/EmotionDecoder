package com.fiap.emotiondecoder

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.widget.addTextChangedListener
import com.fiap.emotiondecoder.databinding.ActivityMainBinding
import com.fiap.emotiondecoder.databinding.CiracaoContaBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth

class CriacaoConta : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    private var binding: CiracaoContaBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CiracaoContaBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        auth = Firebase.auth

        val buttonCadastro = binding?.buttonCriarConta

        buttonCadastro?.setOnClickListener {
            val email: String = binding?.editTextEmail?.text.toString()
            val senha: String = binding?.editTextSenha?.text.toString()
            val confirmarSenha: String = binding?.editTextConfirmarSenha?.text.toString()

            if (email.isEmpty() || senha.isEmpty() || confirmarSenha.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            } else if (senha != confirmarSenha) {
                Toast.makeText(this, "As senhas não correspondem", Toast.LENGTH_SHORT).show()
            } else if (senha.length < 6) {
                Toast.makeText(this, "A senha deve ter no mínimo 6 caracteres", Toast.LENGTH_SHORT).show()
            } else {
                createUserWithEmailAndPassword(email, senha)
            }
        }
    }

    private fun createUserWithEmailAndPassword(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    //val user = auth.currentUser
                    Log.d(TAG, "createUserWithEmailAndPassword: success")
                    navigateToLoginScreen()
                } else {
                    Log.d(TAG, "createUserWithEmailAndPassword: failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed",Toast.LENGTH_SHORT).show()
                }
            }
    }
    private fun navigateToLoginScreen() {
        val intent = Intent(this, TelaLogin::class.java)
        startActivity(intent)
        finish()
    }

    companion object {
        private const val TAG = "EmailAndPassword"
    }
}
