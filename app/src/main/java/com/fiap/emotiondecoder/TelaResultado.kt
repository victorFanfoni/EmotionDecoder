package com.fiap.emotiondecoder

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.google.firebase.auth.FirebaseAuth

class TelaResultado : ComponentActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_resultado)

        val buttonFinalizar = findViewById<Button>(R.id.buttonFinalizar)
        val textoResultado = findViewById<TextView>(R.id.textView)
        val buttonVoltar = findViewById<Button>(R.id.buttonVoltarTelaP)

        val relatorio = intent.getStringExtra(getString(R.string.relatorio))

        textoResultado.text = relatorio

        buttonFinalizar.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, TelaLogin::class.java)
            startActivity(intent)
            finish()
        }

        buttonVoltar.setOnClickListener {
            val intent = Intent(this, PasswordResetActivity::class.java)
            startActivity(intent)
        }

    }
}
