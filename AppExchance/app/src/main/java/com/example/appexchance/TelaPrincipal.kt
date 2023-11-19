package com.example.appexchance

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appexchance.databinding.ActivityTelaPrincipalBinding

class TelaPrincipal : AppCompatActivity() {

    val binding by lazy{
        ActivityTelaPrincipalBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.imgUm.setOnClickListener{
            val opcaoCidade = Intent(this, activity_form_busca::class.java)

            startActivity(opcaoCidade)
        }

        binding.iconPerfil.setOnClickListener{
            val opcaoPerfilHost = Intent(this, TelaUsuarioHost::class.java)

            startActivity(opcaoPerfilHost)
        }
    }
}