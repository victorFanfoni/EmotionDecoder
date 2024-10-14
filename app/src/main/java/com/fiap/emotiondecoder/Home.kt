package com.fiap.emotiondecoder

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import com.fiap.emotiondecoder.ui.view.EmotionalScreening
import com.fiap.emotiondecoder.ui.view.Login
import com.fiap.emotiondecoder.ui.view.UpdateActivity

class Home : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Configura os botões
        val buttonGotoTriagem = findViewById<Button>(R.id.GotoTriagem)
        val buttonUpdate = findViewById<Button>(R.id.buttonUpdate)
        val buttonFinalizar = findViewById<Button>(R.id.buttonFinalizar)

        // Adiciona ações de clique
        buttonGotoTriagem.setOnClickListener {
            // Navegar para a triagem
            val intent = Intent(this, EmotionalScreening::class.java)
            startActivity(intent)
        }

        buttonUpdate.setOnClickListener {
            // Lógica para atualizar cadastro
            val intent = Intent(this, UpdateActivity::class.java)
            startActivity(intent)
        }

        buttonFinalizar.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }
    }
}
