package com.example.appexchance

import RestClient
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.appexchance.databinding.ActivityTelaPerfilHostBinding
import com.example.appexchance.forms.models.Acomodacao
import com.example.appexchance.forms.models.CadastroHostRequest
import com.example.appexchance.forms.models.Reserva
import com.example.appexchance.forms.models.RespostaDadosIntercambista
import com.example.appexchance.utils.SharedPrefsManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TelaPerfilHost : AppCompatActivity() {

    private val bundle by lazy {
        intent.extras
    }

    val binding by lazy {
        ActivityTelaPerfilHostBinding.inflate(layoutInflater)
    }

    private val acomodacao by lazy {
        bundle?.getSerializable("acomodacao") as Acomodacao
    }

    private val api by lazy {
        RestClient.create()
    }

    private val idUser by lazy {
        SharedPrefsManager(this).getInfo().idEstudante
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        displayInfo()
    }

    private fun displayInfo() = with(binding) {
        nomeHost.text = "Familía ${acomodacao.host?.nome}"
        opcaoPerfilHost.text = "Familía ${acomodacao.host?.nome}"
        opcaoPerfilHost.setOnClickListener {
            onBackPressed()
        }
        dataEntrada.setText(acomodacao.inicioDisponibilidade)
        dataSaida.setText(acomodacao.fimDisponibilidade)
        enderecoHome.text = acomodacao.localidade?.endereco
        textoSobre.text = acomodacao.descricao
        cidade.text = acomodacao.localidade?.cidade
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
                        Uri.parse("https://api.whatsapp.com/send?phone=${acomodacao.host?.telefone}")
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

    private fun requestedNewReserve() = with(binding) {
        api.cadastroReserva(
            Reserva(
                estudante = RespostaDadosIntercambista(
                    idEstudante = idUser
                ),
                entrada = dataEntrada.text.toString(),
                saida = dataSaida.text.toString(),
                formaPagamento = "cartão",
                acomodacao = Acomodacao(
                    idAcomodacao = acomodacao.idAcomodacao
                ),
                host = CadastroHostRequest(
                    idHostFamily = acomodacao.host?.idHostFamily ?: 0
                )
            )
        ).enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful) {
                    startActivity(Intent(this@TelaPerfilHost, TelaDeFinalizacao::class.java))
                } else {
                    Log.e("Reserva", "Erro na solicitação de reserva: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Log.e("Reserva", "Falha na solicitação de reserva", t)
            }
        })
    }
}