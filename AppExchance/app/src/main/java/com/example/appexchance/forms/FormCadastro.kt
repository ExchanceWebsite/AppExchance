package com.example.appexchance.forms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appexchance.R
import com.example.appexchance.databinding.ActivityFormCadastroHostBinding
import com.example.appexchance.databinding.ActivityFormLoginBinding

class FormCadastro : AppCompatActivity() {


    val binding by lazy {
        ActivityFormCadastroHostBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }
}