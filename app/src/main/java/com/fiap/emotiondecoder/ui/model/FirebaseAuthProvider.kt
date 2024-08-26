package com.fiap.emotiondecoder.ui.model

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FirebaseAuthProvider : Auth {
    override val auth: FirebaseAuth
        get() = Firebase.auth
}
