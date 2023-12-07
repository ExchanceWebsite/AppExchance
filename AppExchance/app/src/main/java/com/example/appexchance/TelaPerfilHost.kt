package com.example.appexchance

import RestClient
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appexchance.databinding.ActivityTelaPerfilHostBinding
import com.example.appexchance.forms.models.Acomodacao
import com.example.appexchance.forms.models.CadastroHostRequest
import com.example.appexchance.forms.models.CadastroInterRequest
import com.example.appexchance.forms.models.Localidade
import com.example.appexchance.forms.models.Reserva
import com.example.appexchance.forms.models.RespostaDadosIntercambista
import com.example.appexchance.forms.models.RespostaDoServidor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TelaPerfilHost : AppCompatActivity() {

    val binding by lazy {
        ActivityTelaPerfilHostBinding.inflate(layoutInflater)
    }

    private val acomodacao by lazy {
        val bundle = intent.extras
        bundle?.getSerializable("acomodacao") as Acomodacao
    }

    private val api by lazy {
        RestClient.create()
    }

    private val idUser by lazy {

        intent.getStringExtra("id_estudante")

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
        btnReservar.setOnClickListener {
            requestedNewReserve()
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

    private fun requestedNewReserve() {
        Log.d("Reserva", "Iniciando solicitação de reserva")
//        api.cadastroReserva(Reserva().enqueue(object : Callback<Unit> {
//            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
//                // Log após a resposta
//                Log.d("Reserva", "Resposta recebida do servidor")
//                lateinit var telaUsuario: Any
//                val resposta = response.body()
//
//                if (response.isSuccessful) {
//                    telaUsuario = Intent(this@TelaPerfilHost, TelaDeFinalizacao::class.java)
//                    startActivity(telaUsuario)
//                } else {
//                    // Trate os casos de erro aqui
//                    Log.e("Reserva", "Erro na solicitação de reserva: ${response.code()}")
//                }
//            }
//
//            override fun onFailure(call: Call<Unit>, t: Throwable) {
//                // Log em caso de falha
//                Log.e("Reserva", "Falha na solicitação de reserva", t)
//            }
//        })
    }
}