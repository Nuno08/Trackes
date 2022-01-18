package com.example.pmeu

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


    class Registo : AppCompatActivity() {
        lateinit var edit_text_nome: EditText
        lateinit var edit_text_apelido: EditText
        private lateinit var edit_text_password: EditText
        private lateinit var edit_text_Conf_password: EditText
        private lateinit var registo: Button
        lateinit var tvRedirectLogin: TextView

        private lateinit var auth: FirebaseAuth

        override fun onCreate(savedInstanceState: Bundle?) {

            super.onCreate(savedInstanceState)
            setContentView(R.layout.registo)

            // View Bindings
            edit_text_nome = findViewById(R.id.edit_nome)
            edit_text_apelido = findViewById(R.id.edit_apelido)
            edit_text_password = findViewById(R.id.password)
            edit_text_Conf_password = findViewById(R.id.Conf_password)
            registo = findViewById(R.id.registo)
            tvRedirectLogin = findViewById(R.id.login)

            auth = Firebase.auth

            registo.setOnClickListener {
                signUpUser()
            }


            tvRedirectLogin.setOnClickListener {
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
            }

        }

        private fun signUpUser() {
            val nome = edit_text_nome.text.toString()
            val pass = edit_text_apelido.text.toString()
            val confirmPassword = edit_text_Conf_password.text.toString()

            // check pass
            if (nome.isBlank() || pass.isBlank() || confirmPassword.isBlank()) {
                Toast.makeText(this, "Nome e Password não podem estar vazios", Toast.LENGTH_SHORT).show()
                return
            }

            if (pass != confirmPassword) {
                Toast.makeText(this, "Password e Confirmação de Password não correspondem", Toast.LENGTH_SHORT)
                    .show()
                return 0;
            }

            auth.createUserWithEmailAndPassword(nome, pass).addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Registo feito com sucesso", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Registo não valido", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }