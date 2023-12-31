package com.example.appexchance

import RestClient
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.appexchance.adapter.AcomodacoesAdapter
import com.example.appexchance.databinding.ActivityFormBuscaBinding
import com.example.appexchance.forms.models.Acomodacao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TelaFormBusca : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormBuscaBinding.inflate(layoutInflater)
    }

    private val bundle by lazy {
        intent.extras
    }

    private val api by lazy {
        RestClient.create().buscarAcomodacoesPais(
            bundle?.getString("pais") ?: "",
            "1999-12-01",
            "2024-12-01"
        )
    }

    private lateinit var adapter: AcomodacoesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        onBackButton()
        setupRecyclerView()
        requestAccommodations()
    }

    private fun setupRecyclerView() {
        adapter = AcomodacoesAdapter {
            bundle?.putSerializable("acomodacao", it)
            startActivity(Intent(this, TelaPerfilHost::class.java).putExtras(bundle ?: Bundle()))
        }

        binding.recyclerViewListaAcomodacoes.adapter = adapter
    }

    private fun onBackButton() {
        binding.cidade1.apply {
            text = bundle?.getString("pais")
            setOnClickListener {
                onBackPressed()
            }
        }
    }

    private fun requestAccommodations() {
        api.enqueue(object : Callback<List<Acomodacao>> {
            override fun onResponse(
                call: Call<List<Acomodacao>>,
                response: Response<List<Acomodacao>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        if (it.isEmpty()) Log.i("TAG_ACOMODACAO", "Erro na chamada: lista Vazia")
                        else adapter.submitedAcomodacoesList(it)
                    }
                } else {
                    Log.e("TAG_ACOMODACAO", "Erro na chamada: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<Acomodacao>>, t: Throwable) {
                Log.e("TAG_ACOMODACAO", "Falha na chamada: ${t.message}")
            }
        })
    }
}