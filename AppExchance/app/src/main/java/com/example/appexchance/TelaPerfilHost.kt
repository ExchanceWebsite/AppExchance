package com.example.appexchance

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appexchance.databinding.ActivityTelaPerfilHostBinding
import com.example.appexchance.forms.models.Acomodacao

class TelaPerfilHost : AppCompatActivity() {

    val binding by lazy {
        ActivityTelaPerfilHostBinding.inflate(layoutInflater)
    }

    private val acomodacao by lazy {
        val bundle = intent.extras
        bundle?.getSerializable("acomodacao") as Acomodacao
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        displayInfo()
    }

    private fun displayInfo() = with(binding) {

        nomeHost.text = "Familía ${acomodacao.host.nome}"
        opcaoPerfilHost.text = "Familía ${acomodacao.host.nome}"
        opcaoPerfilHost.setOnClickListener {
            onBackPressed()
        }
        dataEntrada.setText(acomodacao.inicioDisponibilidade)
        dataSaida.setText(acomodacao.fimDisponibilidade)
        enderecoHome.text = acomodacao.localidade.endereco
        textoSobre.text = acomodacao.descricao
        cidade.text = acomodacao.localidade.cidade
        valorHome.text = "R$${acomodacao.valorDiaria}"
        buttonWhatsapp.setOnClickListener {
            openWhatsapp()
        }
    }

    private fun openWhatsapp() {
        try {
            startActivity(
                Intent().apply {
                    action = Intent.ACTION_VIEW
                    data =
                        Uri.parse("https://api.whatsapp.com/send?phone=${acomodacao.host.telefone}")
                    setPackage("com.whatsapp")
                }
            )
        } catch (e: ActivityNotFoundException) {
            e.printStackTrace()
            this.startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp&hl=pt_BR")
                setPackage("com.android.vending")
            })
        }
    }
}