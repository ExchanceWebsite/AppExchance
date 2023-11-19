package com.example.appexchance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appexchance.databinding.ActivityTelaUsuarioHostBinding
import com.example.appexchance.databinding.ActivityTelaUsuarioIntercambistaBinding

class TelaUsuarioHost : AppCompatActivity() {

    val binding by lazy {
        ActivityTelaUsuarioHostBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (intent != null) {
            val nome = intent.getStringExtra("txt_nome")
            binding.nomeUsuarioHost.text = nome
        }
    }
}