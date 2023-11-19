package com.example.appexchance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.appexchance.databinding.ActivityTelaUsuarioIntercambistaBinding
import com.example.appexchance.forms.models.RespostaDadosIntercambista
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TelaUsuarioIntercambista : AppCompatActivity() {


    val binding by lazy {
        ActivityTelaUsuarioIntercambistaBinding.inflate(layoutInflater)
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