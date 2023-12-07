package com.example.appexchance

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class TelaDeFinalizacao : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_de_finalizacao)

        val delayMillis: Long = 1500 //

        Handler().postDelayed({
            val intent = Intent(this, TelaPrincipal::class.java)
            startActivity(intent)
            finish()
        }, delayMillis)
    }
}