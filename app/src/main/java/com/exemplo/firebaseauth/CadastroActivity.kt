package com.exemplo.firebaseauth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class CadastroActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        val email = findViewById<EditText>(R.id.emailCadastro)
        val senha = findViewById<EditText>(R.id.senhaCadastro)
        findViewById<Button>(R.id.btnCriarConta).setOnClickListener {
            val e = email.text.toString().trim()
            val s = senha.text.toString()
            if (e.isEmpty() || s.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            } else if (s.length < 6) {
                Toast.makeText(this, "A senha precisa ter 6 caracteres ou mais", Toast.LENGTH_LONG).show()
            } else {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(e, s)
                    .addOnCompleteListener { tarefa ->
                        if (tarefa.isSuccessful) {
                            Toast.makeText(this, "Cadastro realizado!", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, PerfilActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(this, "Erro no cadastro: ${tarefa.exception?.localizedMessage}", Toast.LENGTH_LONG).show()
                        }
                    }
            }
        }
    }
}
