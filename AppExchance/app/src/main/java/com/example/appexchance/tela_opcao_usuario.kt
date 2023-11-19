package com.example.appexchance

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appexchance.databinding.ActivityTelaOpcaoUsuarioBinding
import com.example.appexchance.forms.FormCadastro
import com.example.appexchance.forms.Form_Cadastro_Intercambista

class tela_opcao_usuario : AppCompatActivity() {

    val binding by lazy {
        ActivityTelaOpcaoUsuarioBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.buttunInter.setOnClickListener {
            val intent = Intent(this, Form_Cadastro_Intercambista::class.java)
            startActivity(intent)
        }


        binding.buttonHost.setOnClickListener {
            val opcaoCadastroHost = Intent(this, FormCadastro::class.java)

            startActivity(opcaoCadastroHost)
        }

        binding.buttonPular.setOnClickListener {
            val opcaoPular = Intent(this, TelaPrincipal::class.java)

            startActivity(opcaoPular)
        }

    }
}