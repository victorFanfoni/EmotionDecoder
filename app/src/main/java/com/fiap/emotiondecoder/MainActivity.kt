package com.fiap.emotiondecoder

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import android.widget.Button
import android.content.Intent

class MainActivity : ComponentActivity() {

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonStart: Button = findViewById(R.id.button_start)

        buttonStart.setOnClickListener {
          val navegar = Intent(this, TelaLogin::class.java)
            startActivity(navegar)
            finish()
        }
    }
}