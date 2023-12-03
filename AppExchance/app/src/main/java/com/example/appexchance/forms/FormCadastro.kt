package com.example.appexchance.forms

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.appexchance.R
import com.example.appexchance.databinding.ActivityFormCadastroHostBinding
import com.example.appexchance.databinding.ActivityFormLoginBinding
import com.example.appexchance.forms.models.CadastroHostRequest
import com.example.appexchance.forms.models.CadastroInterRequest
import com.example.appexchance.forms.models.Localidade
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FormCadastro : AppCompatActivity() {


    val binding by lazy {
        ActivityFormCadastroHostBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        fun fazerCadastro() {
            val nome = binding.editNomeHost.text.toString()
            val email = binding.editEmail.text.toString()
            val senha = binding.editSenhaHost.text.toString()
            val descricao = binding.editDescricaoHost.text.toString()
            val verificado = binding.editVerificadoHost.text.toString()
            val senhaConfirma = binding.editConfirmeSenhaHost.text.toString()

            val Localizacao = Localidade(1, "", "", "", "")


            val cadastroHostRequest =
                CadastroHostRequest(nome, verificado, descricao, email, senha, Localizacao)
            val apiService = RestClient.create()

            Log.d("Cadastro", cadastroHostRequest.toString())

            Log.d("Cadastro", Localizacao.idLocalidade.toString())


            val call = apiService.cadastroHost(cadastroHostRequest)

            call.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {

                        Toast.makeText(
                            this@FormCadastro,
                            "Cadastro realizado com sucesso!!!",
                            Toast.LENGTH_SHORT
                        ).show()
                        val intent = Intent(this@FormCadastro, FormLogin::class.java)
                        startActivity(intent)

                    } else {
                        Toast.makeText(
                            this@FormCadastro,
                            "Dados de cadastro invalidos!!!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(this@FormCadastro, "Erro de rede!!!", Toast.LENGTH_SHORT).show()
                    t.printStackTrace()
                }
            })
        }



        binding.buttonAcessar.setOnClickListener {
            fazerCadastro()
        }
    }
}