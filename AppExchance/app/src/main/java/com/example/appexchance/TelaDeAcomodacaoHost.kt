package com.example.appexchance

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.get
import com.example.appexchance.databinding.ActivityTelaDeAcomodacaoHostBinding
import com.example.appexchance.databinding.ActivityTelaPrincipalHostBinding
import com.example.appexchance.forms.models.Acomodacao
import com.example.appexchance.forms.models.CadastroHostRequest
import com.example.appexchance.forms.models.Localidade
import com.example.appexchance.utils.SharedPrefsManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate

class TelaDeAcomodacaoHost : AppCompatActivity() {

    val binding by lazy {
        ActivityTelaDeAcomodacaoHostBinding.inflate(layoutInflater)
    }


    private val api by lazy {
        RestClient.create()
    }



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.entrada.setText(LocalDate.now().toString())


        requestedNewAcomod()
    }

    private fun requestedNewAcomod() = with(binding) {
        binding.btnCadastrarAcomodacao.setOnClickListener {
            val cep = campoCep.text.toString().take(8)
            val cidade = campoCidade.text.toString().take(45)
            val pais = campoPais.text.toString().take(45)
            val endereco = campoEndereco.text.toString().take(45)


            api.cadastroLocalidade(
                Localidade(
                    pais = pais,
                    cidade = cidade,
                    endereco = endereco,
                    cep = cep
                )
            ).enqueue(object : Callback<Localidade> {
                override fun onResponse(call: Call<Localidade>, response: Response<Localidade>) {
                    if (response.isSuccessful) {
                        response.body()?.let {

                            api.cadastroAcomodacao(
                                Acomodacao(
                                    host = CadastroHostRequest(
                                        idHostFamily = SharedPrefsManager(this@TelaDeAcomodacaoHost).getInfo().idHostFamily
                                    ),
                                    localidade = Localidade(
                                        idLocalidade =  it.idLocalidade
                                    ),
                                    reservado = false,
                                    reserva = null,
                                    descricao = descricaoAcomodacao.text.toString(),
                                    inicioDisponibilidade = entrada.text.toString(),
                                    fimDisponibilidade = saida.text.toString(),
                                    valorDiaria = campoDiaria.text.toString().toInt(),
                                    regras = campoRegras.toString()
                                )
                            ).enqueue(object : Callback<Unit> {
                                override fun onResponse(
                                    call: Call<Unit>,
                                    response: Response<Unit>
                                ) {
                                    if (response.isSuccessful) {
                                        startActivity(
                                            Intent(
                                                this@TelaDeAcomodacaoHost,
                                                TelaPrincipalHost::class.java
                                            )
                                        )
                                        Toast.makeText(
                                            this@TelaDeAcomodacaoHost,
                                            "Acomodação cadastrada!!!",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    } else {
                                        Log.e(
                                            "Acomodacao",
                                            "Erro na solicitação: ${response.code()}"
                                        )
                                        Toast.makeText(
                                            this@TelaDeAcomodacaoHost,
                                            "erro no cadastro!!!",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    }
                                }

                                override fun onFailure(call: Call<Unit>, t: Throwable) {
                                    Log.e("Acomodacao", "Falha na solicitação", t)
                                }
                            })
                        }
                        Log.e("Localidade", "Localidade cadastrada: ${response.body()}")
                    } else {
                        Log.e("Localidade", "Erro na solicitação: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<Localidade>, t: Throwable) {
                    Log.e("Localidade", "Falha na solicitação", t)
                }
            })
        }
    }


}