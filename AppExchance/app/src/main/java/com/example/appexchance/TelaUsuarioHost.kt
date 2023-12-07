package com.example.appexchance

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appexchance.databinding.ActivityTelaUsuarioHostBinding
import com.example.appexchance.utils.SharedPrefsManager

class TelaUsuarioHost : AppCompatActivity() {

    val binding by lazy {
        ActivityTelaUsuarioHostBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.nomeUsuarioHost.text = "Familía ${SharedPrefsManager(this).getInfo().nome}"
    }
}