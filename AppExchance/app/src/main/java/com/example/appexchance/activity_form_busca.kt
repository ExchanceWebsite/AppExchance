package com.example.appexchance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import com.example.appexchance.databinding.ActivityFormBuscaBinding
import com.example.appexchance.forms.ApiService
import com.example.appexchance.forms.models.Acomodacao
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

class activity_form_busca : AppCompatActivity() {

    val binding by lazy {
        ActivityFormBuscaBinding.inflate(layoutInflater)
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val mainLinearLayout = findViewById<ScrollView>(R.id.acomodacoes)

        fun renderCards(cardDataList: List<Acomodacao>) {

            for (Acomodacao in cardDataList) {
                val cardView = LayoutInflater.from(baseContext).inflate(R.layout.card_layout, null)

                val titleTextView = cardView.findViewById<TextView>(R.id.text_familia)
                titleTextView.text = Acomodacao.host.nome

                val descriptionTextView = cardView.findViewById<TextView>(R.id.text_desc)
                descriptionTextView.text =  Acomodacao.descricao

                // Adicione o cardView ao seu layout principal
                // Exemplo: linearLayout.addView(cardView)

                mainLinearLayout.addView(cardView)

            }
        }

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val apiService = RestClient.create()
                val response = apiService.buscarAcomodacoesPais(binding.cidade1.toString(),"2023-12-01","2023-12-08")
                withContext(Dispatchers.Main) {
                    renderCards(response)
                }
            } catch (e: Exception) {

            }
        }



    }
}