    package com.fiap.emotiondecoder

    import android.app.AlertDialog
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
    import com.google.firebase.auth.ktx.auth

    class CriacaoConta : ComponentActivity() {
        private lateinit var auth: FirebaseAuth
        private var binding: CiracaoContaBinding? = null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = CiracaoContaBinding.inflate(layoutInflater)
            setContentView(binding?.root)

            auth = Firebase.auth

            val buttonCadastro = binding?.buttonCriarConta

            binding?.VoltaLogin?.setOnClickListener {
                val intent = Intent(this, TelaLogin::class.java)
                startActivity(intent)
                finish()
            }

            buttonCadastro?.setOnClickListener {

                val email = binding?.editTextEmail?.text.toString()
                val senha = binding?.editTextSenha?.text.toString()
                val confirmaSenha = binding?.editTextConfirmarSenha?.text.toString()

                if (email.isEmpty() || senha.isEmpty() || confirmaSenha.isEmpty()){
                    binding?.editTextEmail?.error = getString(R.string.email)
                    binding?.editTextSenha?.error = getString(R.string.senhaAlert)
                    binding?.editTextConfirmarSenha?.error = getString(R.string.campoConfirSenha)
                    Toast.makeText(this, getString(R.string.preencha_Todos_campos), Toast.LENGTH_SHORT).show()
                }
                else if (!email.contains("@")){
                    showAlert(getString(R.string.Emailerr), getString(R.string.exEmail))
                    Toast.makeText(this, getString(R.string.Emailerr), Toast.LENGTH_SHORT).show()
                }
                else if (senha != confirmaSenha){
                    Toast.makeText(this, getString(R.string.senhaigual), Toast.LENGTH_SHORT).show()
                }
                else if (senha.length <= 5){
                    Toast.makeText(this, getString(R.string.qtdCaracSenha), Toast.LENGTH_SHORT).show()
                }
                else {
                    createAccount(email, senha)
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

        companion object {
            private const val TAG = "EmailAndPassword"
        }
        fun createAccount (email: String, password: String) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = com.google.firebase.ktx.Firebase.auth.currentUser
                        user!!.sendEmailVerification()
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    println("Email sent.")
                                }
                            }
                        Toast.makeText(this, getString(R.string.cadastroOk), Toast.LENGTH_LONG).show()
                        val intent = Intent(this, TelaLogin::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, getString(R.string.usuario_ja_cadastrado), Toast.LENGTH_LONG).show()
                    }
                }
        }
    }