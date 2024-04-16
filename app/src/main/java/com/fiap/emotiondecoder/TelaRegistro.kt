package com.fiap.emotiondecoder

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity

class TelaRegistro : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_de_registro)

        val nome = findViewById<EditText>(R.id.edit_nome)
        val email = findViewById<EditText>(R.id.edit_email)
        val senha = findViewById<EditText>(R.id.edit_senha)
        val confirmacaoSenha = findViewById<EditText>(R.id.edit_confirmar_senha)
        val buttonCadastrar = findViewById<Button>(R.id.button_criar_conta)
        val buttonEntrar = findViewById<Button>(R.id.button_entrar)
        val esqueciSenha = findViewById<TextView>(R.id.text_esqueci_senha)

        buttonCadastrar.setOnClickListener {
            val navegar = Intent(this, CriacaoConta::class.java)
            startActivity(navegar)
        }

        buttonEntrar.setOnClickListener {

            val nome = nome.text.toString()
            val email = email.text.toString()
            val senha = senha.text.toString()
            val confirmacaoSenha = confirmacaoSenha.text.toString()

            if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || confirmacaoSenha.isEmpty()) {
                showAlert("Ops!", "Preencha todos os campos")
            }

            if (nome != confirmacaoSenha){
                Toast.makeText(this, "As senhas não coincidem", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            else {
                val navegar = Intent(this, TriagemEmocional::class.java)
                startActivity(navegar)
            }
        }

        esqueciSenha.setOnClickListener{
            alterarConta("Nova senha", "Senha nova: ")
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
    private fun alterarConta(title: String, message: String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("Ok"){dialog, _ -> dialog.dismiss()}
        val dialog = builder.create()
        dialog.show()

    }
}