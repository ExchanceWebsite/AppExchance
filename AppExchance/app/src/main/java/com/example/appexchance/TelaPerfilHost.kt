package com.example.appexchance

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.appexchance.databinding.ActivityTelaPerfilHostBinding

class TelaPerfilHost : AppCompatActivity() {


    val binding by lazy {
        ActivityTelaPerfilHostBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    fun abrirWhatsapp( view: View){
        val numeroTelefone = "11954337632"

        val uri = Uri.parse("https://api.whatsapp.com/send?phone=$numeroTelefone")

        val intent = Intent(Intent.ACTION_VIEW,uri)

        if(intent.resolveActivity(packageManager) != null){
            startActivity(intent)
        } else{
            Toast.makeText( this@TelaPerfilHost, "Baixe o Aplicativo do WhatsApp por favor!",Toast.LENGTH_SHORT).show()
        }
    }
}