    package com.fiap.emotiondecoder

    import android.content.Intent
    import android.os.Bundle
    import android.util.Log
    import android.widget.Button
    import android.widget.EditText
    import android.widget.Toast
    import androidx.activity.ComponentActivity
    import androidx.core.widget.addTextChangedListener
    import com.fiap.emotiondecoder.databinding.ActivityMainBinding
    import com.fiap.emotiondecoder.databinding.CiracaoContaBinding
    import com.google.android.material.snackbar.Snackbar
    import com.google.firebase.Firebase
    import com.google.firebase.auth.FirebaseAuth
    import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
    import com.google.firebase.auth.FirebaseAuthUserCollisionException
    import com.google.firebase.auth.FirebaseAuthWeakPasswordException
    import com.google.firebase.auth.FirebaseUser
    import com.google.firebase.auth.auth

    class CriacaoConta : ComponentActivity() {
        private lateinit var auth: FirebaseAuth
        private var binding: CiracaoContaBinding? = null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = CiracaoContaBinding.inflate(layoutInflater)
            setContentView(binding?.root)

            auth = Firebase.auth

            val buttonCadastro = binding?.buttonCriarConta

            buttonCadastro?.setOnClickListener {

                val email: String = binding?.editTextEmail?.text.toString()
                val senha: String = binding?.editTextSenha?.text.toString()
                val confirmarSenha: String = binding?.editTextConfirmarSenha?.text.toString()

                when {
                    email.isEmpty() -> binding?.editTextEmail?.error = "Preencha o E-mail!"
                    !email.contains("@") -> mostrarSnackbar("E-mail inválido!")
                    senha.isEmpty() -> binding?.editTextSenha?.error = "Preencha a senha!!"
                    confirmarSenha.isEmpty() -> binding?.editTextConfirmarSenha?.error = "Preencha a confirmaçao de senha!!"
                    senha != confirmarSenha -> mostrarSnackbar("As senhas devem ser identicas")
                    senha.length <= 5 -> mostrarSnackbar("A senha deve ter pelo menos 6 caracteres!")
                    else -> createUserWithEmailAndPassword(email, senha)

                }

    //            if (email.isEmpty() || senha.isEmpty() || confirmarSenha.isEmpty()) {
    //                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
    //            } else if (senha != confirmarSenha) {
    //                Toast.makeText(this, "As senhas não correspondem", Toast.LENGTH_SHORT).show()
    //            } else if (senha.length < 6) {
    //                Toast.makeText(this, "A senha deve ter no mínimo 6 caracteres", Toast.LENGTH_SHORT).show()
    //            } else {
    //                createUserWithEmailAndPassword(email, senha)
    //            }
            }
        }

        private fun createUserWithEmailAndPassword(email: String, password: String) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "createUserWithEmailAndPassword: success")
                        navigateToLoginScreen()
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d(TAG, "createUserWithEmailAndPassword: failure", exception)
                    when (exception) {
                        is FirebaseAuthWeakPasswordException -> {
                            binding?.editTextSenha?.error = "A senha deve ter pelo menos 6 caracteres"
                        }
                        is FirebaseAuthInvalidCredentialsException -> {
                            binding?.editTextEmail?.error = "E-mail inválido"
                        }
                        is FirebaseAuthUserCollisionException -> {
                            binding?.editTextEmail?.error = "Este e-mail já está em uso"
                        }
                        else -> {
                            Toast.makeText(
                                this@CriacaoConta,
                                "Erro ao criar a conta: ${exception.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
        }

        private fun mostrarSnackbar(mensagem: String) {
            val snackbar = Snackbar.make(binding!!.root, mensagem, Snackbar.LENGTH_SHORT)
            snackbar.show()
        }

        private fun navigateToLoginScreen() {
            val intent = Intent(this, TelaLogin::class.java)
            startActivity(intent)
            finish()
        }

        companion object {
            private const val TAG = "EmailAndPassword"
        }
    }
