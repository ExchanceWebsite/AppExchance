package com.example.appexchance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appexchance.databinding.ActivityFormBuscaBinding

class activity_form_busca : AppCompatActivity() {

    val binding by lazy {
        ActivityFormBuscaBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}