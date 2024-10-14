package com.fiap.emotiondecoder

import android.os.Bundle
import androidx.activity.ComponentActivity
import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.fiap.emotiondecoder.ui.model.FirebaseAuthProvider
import com.fiap.emotiondecoder.ui.view.Login

class MainActivity : ComponentActivity() {

    private lateinit var authProvider: FirebaseAuthProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        authProvider = FirebaseAuthProvider()

        Handler(Looper.getMainLooper()).postDelayed({
            checkAuthentication()
        }, 1000)
    }
    private fun checkAuthentication() {
        val currentUser = authProvider.auth.currentUser
        if (currentUser != null) {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
            finish()
        } else {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }
    }
}
