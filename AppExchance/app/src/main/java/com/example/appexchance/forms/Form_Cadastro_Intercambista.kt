package com.example.appexchance.forms


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.example.appexchance.databinding.ActivityFormCadastroIntercambistaBinding
import com.example.appexchance.forms.models.CadastroInterRequest
import com.example.appexchance.forms.models.Localidade
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Form_Cadastro_Intercambista : AppCompatActivity() {

    val binding by lazy {
        ActivityFormCadastroIntercambistaBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        fun fazerCadastro() {
            val nome = binding.editNomeIntercambista.text.toString()
            val email = binding.editEmailIntercambista.text.toString()
            val senha = binding.editSenhaIntercambista.text.toString()

            val localidade: Localidade? = null;

            if (localidade != null) {
                localidade.idLocalidade = 1
            };

            val CadastroInterRequest = CadastroInterRequest(nome, 22, "Estudioso e bem prestativo", email, senha, "11980927174", "47885458881", localidade)
            val apiService = RestClient.create()

            val call = apiService.cadastro(CadastroInterRequest)

            call.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {

                        Toast.makeText(this@Form_Cadastro_Intercambista, "Cadastro realizado com sucesso!!!", Toast.LENGTH_SHORT).show()

                    } else {
                        Toast.makeText(this@Form_Cadastro_Intercambista, "Dados de cadastro invalidos!!!", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(this@Form_Cadastro_Intercambista, "Erro de rede!!!", Toast.LENGTH_SHORT).show()
                    t.printStackTrace()
                }
            })
        }



        binding.buttonAcessar.setOnClickListener {
            fazerCadastro()
        }










    }



}