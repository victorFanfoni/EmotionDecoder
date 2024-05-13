package com.fiap.emotiondecoder

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.fiap.emotiondecoder.databinding.ActivityPasswordResetBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class PasswordResetActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth
    private var binding: ActivityPasswordResetBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPasswordResetBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        auth = FirebaseAuth.getInstance()


        binding?.VoltaLogin?.setOnClickListener {
            val intent = Intent(this, TelaLogin::class.java)
            startActivity(intent)
            finish()
        }

        binding?.buttonResetPassword?.setOnClickListener {
            val email = binding?.editEmail?.text.toString().trim()

            if (email.isEmpty()) {
                binding?.editEmail?.error = getString(R.string.email)
            } else if (!email.contains("@")) {
                mostrarSnackbar(getString(R.string.Emailerr))
            } else {
                auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, getString(R.string.email_redefini_senha), Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, TelaLogin::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, getString(R.string.falha_email_redefinSenha), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
    private fun mostrarSnackbar(mensagem: String) {
        Snackbar.make(binding?.root ?: return, mensagem, Snackbar.LENGTH_SHORT).show()
    }
}