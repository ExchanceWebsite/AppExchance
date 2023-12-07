package com.example.appexchance

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appexchance.databinding.ActivityTelaUsuarioIntercambistaBinding
import com.example.appexchance.utils.SharedPrefsManager

class TelaUsuarioIntercambista : AppCompatActivity() {

    val binding by lazy {
        ActivityTelaUsuarioIntercambistaBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.nomeUsuarioHost.text = "Bem vindo(a) ${SharedPrefsManager(this).getInfo().nome}"
    }
}