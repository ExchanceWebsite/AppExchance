package com.example.appexchance

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.appexchance.forms.FormLogin


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_carregamento)

        val delayMillis: Long = 1500 //

        Handler().postDelayed({
            val intent = Intent(this, tela_opcoes::class.java)
            startActivity(intent)
            finish()
        }, delayMillis)

    }
}