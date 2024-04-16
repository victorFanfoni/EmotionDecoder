package com.fiap.emotiondecoder

import android.os.Bundle
import androidx.activity.ComponentActivity
import android.widget.Button

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonStart: Button = findViewById(R.id.button_start)
        buttonStart.setOnClickListener {
            // Ação ao clicar no botão "Começar", você pode adicionar aqui
        }
    }
}