package com.example.appexchance

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appexchance.databinding.ActivityTelaPrincipalHostBinding
import com.example.appexchance.databinding.ActivityTelaUsuarioHostBinding

class TelaPrincipalHost : AppCompatActivity() {


    val binding by lazy {
        ActivityTelaPrincipalHostBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val nome = intent.getStringExtra("txt_nome")
        if (intent != null) {
            binding.bemvindoHost.text = "Bem vinda Familia " + nome
        }


        binding.iconPerfil.setOnClickListener {
            lateinit var telaUsuario: Any
            telaUsuario = Intent(this@TelaPrincipalHost, TelaUsuarioHost::class.java)

            telaUsuario.putExtra("txt_nome", nome)

            startActivity(telaUsuario)
        }

        binding.buttonAdicionarAcomodacao.setOnClickListener {
            lateinit var telaUsuario: Any

            telaUsuario = Intent(this@TelaPrincipalHost, TelaDeAcomodacaoHost::class.java)

            startActivity(telaUsuario as Intent)
        }

    }
}