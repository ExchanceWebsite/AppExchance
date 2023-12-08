package com.example.appexchance

import RestClient
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.appexchance.adapter.AcomodacoesHostAdapter
import com.example.appexchance.databinding.ActivityTelaPrincipalHostBinding
import com.example.appexchance.forms.models.Acomodacao
import com.example.appexchance.utils.SharedPrefsManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TelaPrincipalHost : AppCompatActivity() {

    val binding by lazy {
        ActivityTelaPrincipalHostBinding.inflate(layoutInflater)
    }

    private val api by lazy {
        RestClient.create().buscarAcomodacoesHost(SharedPrefsManager(this).getInfo().idHostFamily)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        displayContentScreen()
        requestedList()
        addAcomodacao()
        Log.i("TEST", (SharedPrefsManager(this).getInfo().idEstudante).toString())
    }

    private fun displayContentScreen() = with(binding) {
        bemvindoHost.text =
            "Bem vindo(a) ${SharedPrefsManager(this@TelaPrincipalHost).getInfo().nome}"

        iconPerfil.setOnClickListener {
            startActivity(Intent(this@TelaPrincipalHost, TelaUsuarioHost::class.java))
        }
    }

    private fun requestedList() {
        api.enqueue(object : Callback<List<Acomodacao>> {
            override fun onResponse(
                call: Call<List<Acomodacao>>,
                response: Response<List<Acomodacao>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        setupRecyclerView(it)
                    }
                } else {
                    Log.e("EMPTY", "Lista vazia.")
                }
            }

            override fun onFailure(call: Call<List<Acomodacao>>, t: Throwable) {
                Log.e("ERROR", "Falha na solicitação das acomodações", t)
            }
        })
    }

    private fun setupRecyclerView(list: List<Acomodacao>) {
        binding.rvAcomodacoes.adapter = AcomodacoesHostAdapter(list)
    }

    private fun addAcomodacao(){
        binding.btnCadastroAcomodacao.setOnClickListener {
            startActivity(Intent(this@TelaPrincipalHost, TelaDeAcomodacaoHost::class.java))
        }
    }
}