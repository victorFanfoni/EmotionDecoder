package com.fiap.emotiondecoder

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity



class CriacaoConta : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.criacao_conta)

        val nome = findViewById<EditText>(R.id.editTextNome)
        val email = findViewById<EditText>(R.id.editTextEmail)
        val senha = findViewById<EditText>(R.id.editTextSenha)
        val confirmacaoSenha = findViewById<EditText>(R.id.editTextConfirmarSenha)
        val buttonCadastro = findViewById<Button>(R.id.buttonCriarConta)

        buttonCadastro.setOnClickListener {
            val nomeString = nome.text.toString()
            val emailString = email.text.toString()
            val senhaString = senha.text.toString()
            val confirmacaoSenhaString = confirmacaoSenha.text.toString()

            if (nomeString.isEmpty() || emailString.isEmpty() || senhaString.isEmpty() || confirmacaoSenhaString.isEmpty()) {
                showAlert("Ops!", "Preencha todos os campos")
            }else {
                val navegar = Intent(this, TelaRegistro::class.java)
                startActivity(navegar)
            }
        }
    }
    private fun showAlert(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("Ok"){dialog, _ -> dialog.dismiss()}
        val dialog = builder.create()
        dialog.show()
    }
}
