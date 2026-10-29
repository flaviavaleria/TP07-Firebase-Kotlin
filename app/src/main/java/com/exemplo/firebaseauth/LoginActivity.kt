package com.exemplo.firebaseauth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : Activity() {
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val email = findViewById<EditText>(R.id.emailLogin)
        val senha = findViewById<EditText>(R.id.senhaLogin)

        findViewById<Button>(R.id.btnEntrar).setOnClickListener {
            val e = email.text.toString().trim()
            val s = senha.text.toString()
            if (e.isEmpty() || s.isEmpty()) {
                Toast.makeText(this, "Preencha e-mail e senha", Toast.LENGTH_SHORT).show()
            } else {
                auth.signInWithEmailAndPassword(e, s).addOnCompleteListener { tarefa ->
                    if (tarefa.isSuccessful) {
                        startActivity(Intent(this, PerfilActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "Não foi possível entrar. Confira os dados.", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
        findViewById<Button>(R.id.btnCadastro).setOnClickListener {
            startActivity(Intent(this, CadastroActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null) {
            startActivity(Intent(this, PerfilActivity::class.java))
            finish()
        }
    }
}
