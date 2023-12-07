package com.example.appexchance

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appexchance.adapter.CountryAdapter
import com.example.appexchance.databinding.ActivityTelaPrincipalBinding
import com.example.appexchance.forms.models.Pais
import com.example.appexchance.utils.SharedPrefsManager

class TelaPrincipal : AppCompatActivity() {

    val binding by lazy {
        ActivityTelaPrincipalBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupToolbar()
        setupRecyclerView()
    }

    private fun setupToolbar() {
        binding.msgBemVindo.text = "Bem vindo(a) ${SharedPrefsManager(this).getInfo().nome}"
        binding.iconPerfil.setOnClickListener {
            startActivity(Intent(this, TelaUsuarioIntercambista::class.java))
        }
    }

    private fun setupRecyclerView() {
        val countryAdapter = CountryAdapter(getData()) { pais ->
            val bundle = Bundle()
            bundle.putString("pais", pais)
            startActivity(
                Intent(this@TelaPrincipal, TelaFormBusca::class.java).putExtras(bundle)
            )
        }

        binding.rvCities.apply {
            layoutManager = GridLayoutManager(this@TelaPrincipal, 3)
            adapter = countryAdapter
        }
        setupSearchField(countryAdapter)
    }

    private fun setupSearchField(adapter: CountryAdapter) {
        binding.barraPesquisa.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }
        })
    }

    private fun getData() = listOf(
        Pais(
            imagem = "https://s3.static.brasilescola.uol.com.br/be/2021/07/bandeira-francesa.jpg",
            nome = "França"
        ),
        Pais(
            imagem = "https://img.freepik.com/fotos-gratis/bandeira-do-brasil_1401-76.jpg",
            nome = "Brasil"
        ),
        Pais(
            imagem = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR8MHE7yjmu4moWxnVwyCY71MdITHbx6guYtA&usqp=CAU",
            nome = "Inglaterra"
        ),
        Pais(
            imagem = "https://media.istockphoto.com/id/511092543/pt/foto/grande-plano-da-bandeira-do-jap%C3%A3o.jpg?s=612x612&w=0&k=20&c=l-C2YzhT331s4GuUXoUcD4ieqDEjX_RyuDElOI5OgNo=",
            nome = "Japão"
        )
    )
}