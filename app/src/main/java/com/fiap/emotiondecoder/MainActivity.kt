package com.fiap.emotiondecoder

import android.os.Bundle
import androidx.activity.ComponentActivity
import android.widget.Button
import android.content.Intent
import com.fiap.emotiondecoder.ui.model.FirebaseAuthProvider
import com.fiap.emotiondecoder.ui.view.EmotionalScreening
import com.fiap.emotiondecoder.ui.view.Login

class MainActivity : ComponentActivity() {

    private lateinit var authProvider: FirebaseAuthProvider

    override fun onStart() {
        super.onStart()
        val currentUser = authProvider.auth.currentUser
        if (currentUser != null) {
            reload()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        authProvider = FirebaseAuthProvider()

        val buttonStart: Button = findViewById(R.id.button_start)

        buttonStart.setOnClickListener {
            val navegar = Intent(this, Login::class.java)
            startActivity(navegar)
            finish()
        }
    }

    private fun reload() {
        val intent = Intent(this, EmotionalScreening::class.java)
        startActivity(intent)
        finish()
    }
}
