package com.example.appexchance

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appexchance.databinding.ActivityTelaOpcoesBinding

import com.example.appexchance.forms.FormCadastro
import com.example.appexchance.forms.FormLogin

class tela_opcoes : AppCompatActivity() {

    val binding by lazy {
        ActivityTelaOpcoesBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, FormLogin::class.java)
            startActivity(intent)
        }

        binding.btnCadastro.setOnClickListener {
            val intent = Intent(this, tela_opcao_usuario::class.java)
            startActivity(intent)
        }


        binding.buttonPular.setOnClickListener {
            val opcaoPular = Intent(this, TelaPrincipal::class.java)
            startActivity(opcaoPular)
        }

    }



}