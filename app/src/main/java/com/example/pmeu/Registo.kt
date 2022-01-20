package com.example.pmeu

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase



class Registo : AppCompatActivity() {
        lateinit var edit_text_email: EditText
        private lateinit var edit_text_password: EditText
        private lateinit var edit_text_Conf_password: EditText
        private lateinit var registo: Button
        lateinit var tvRedirectLogin: TextView

        private lateinit var auth: FirebaseAuth

        override fun onCreate(savedInstanceState: Bundle?) {

            super.onCreate(savedInstanceState)
            setContentView(R.layout.registo)

            // View Bindings
            edit_text_email = findViewById(R.id.edit_email)
            edit_text_password = findViewById(R.id.password)
            edit_text_Conf_password = findViewById(R.id.Conf_password)
            registo = findViewById(R.id.registo)
            tvRedirectLogin = findViewById(R.id.login)

            auth = Firebase.auth

            registo.setOnClickListener {
                signUpUser()
            }


            tvRedirectLogin.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

        }

        private fun signUpUser() {
            val email = edit_text_email.text.toString()
            val pass = edit_text_password.text.toString()
            val confirmPassword = edit_text_Conf_password.text.toString()


            if (email.isBlank() || pass.isBlank() || confirmPassword.isBlank()) {
                Toast.makeText(this, "Nome e Password não podem estar vazios", Toast.LENGTH_SHORT).show()
                return
            }

            if (pass != confirmPassword) {
                Toast.makeText(this, "Password e Confirmação de Password não correspondem", Toast.LENGTH_SHORT)
                    .show()
                return
            }

            auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Registo feito com sucesso", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Registo não valido", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }