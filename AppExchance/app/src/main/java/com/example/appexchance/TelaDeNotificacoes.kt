package com.example.appexchance

import RestClient
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.appexchance.adapter.ReservasAdapter
import com.example.appexchance.databinding.ActivityTelaDeNotificacoesBinding
import com.example.appexchance.forms.models.Reserva
import com.example.appexchance.utils.SharedPrefsManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TelaDeNotificacoes : AppCompatActivity() {
    private val binding by lazy {
        ActivityTelaDeNotificacoesBinding.inflate(layoutInflater)
    }

    private val api by lazy {
        RestClient.create().buscarReservasEstudante(SharedPrefsManager(this).getInfo().idEstudante)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        requestedReservas()
        setupOnClick()
        setupToolbar()
    }

    private fun setupToolbar() {
        binding.tituloEstudante.text = "Bem vindo(a) ${SharedPrefsManager(this).getInfo().nome}"
    }

    private fun requestedReservas() {
        api.enqueue(object : Callback<List<Reserva>> {
            override fun onResponse(call: Call<List<Reserva>>, response: Response<List<Reserva>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        setupRecyclerView(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<Reserva>>, t: Throwable) {
                Log.e("ERROR", "Erro na requisição", t)
            }
        })
    }

    private fun setupRecyclerView(list: List<Reserva>) {
        binding.rvReservas.adapter = ReservasAdapter(list)
    }

    private fun setupOnClick() = with(binding) {
        menuCasa.setOnClickListener {
            startActivity(Intent(this@TelaDeNotificacoes, TelaPrincipal::class.java))
        }
        menuReserva.setOnClickListener {
            startActivity(Intent(this@TelaDeNotificacoes, TelaDeNotificacoes::class.java))
        }
        menuPerfil.setOnClickListener {
            startActivity(Intent(this@TelaDeNotificacoes, TelaUsuarioIntercambista::class.java))
        }
    }
}