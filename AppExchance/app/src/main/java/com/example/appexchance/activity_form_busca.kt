package com.example.appexchance

import RestClient
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.appexchance.adapter.AcomodacoesAdapter
import com.example.appexchance.databinding.ActivityFormBuscaBinding
import com.example.appexchance.forms.models.Acomodacao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class activity_form_busca : AppCompatActivity() {

    lateinit var adapter: AcomodacoesAdapter

    val binding by lazy {
        ActivityFormBuscaBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        adapter = AcomodacoesAdapter()

        binding.recyclerViewListaAcomodacoes.adapter = adapter

        val apiService = RestClient.create()

        val call: Call<List<Acomodacao>> = apiService.buscarAcomodacoesPais(
            "Paris",
            "2023-12-01",
            "2023-12-08"
        )

        call.enqueue(object : Callback<List<Acomodacao>> {
            override fun onResponse(call: Call<List<Acomodacao>>, response: Response<List<Acomodacao>>) {
                if (response.isSuccessful) {
                    val listaAcomodacoes: List<Acomodacao>? = response.body()

                    if (listaAcomodacoes != null) {
                        adapter.submitedAcomodacoesList(listaAcomodacoes)
                    }else{
                        Log.i("TAG_ACOMODACAO", "Erro na chamada: lista Vazia")
                    }

                } else {
                    // Tratar erro na resposta da chamada
                    Log.e("TAG_ACOMODACAO", "Erro na chamada: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<Acomodacao>>, t: Throwable) {
                // Tratar falha na chamada
                Log.e("TAG_ACOMODACAO", "Falha na chamada: ${t.message}")
            }
        })

    }
}