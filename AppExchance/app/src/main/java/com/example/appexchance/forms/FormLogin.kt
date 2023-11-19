package com.example.appexchance.forms

import android.content.Intent
import com.example.appexchance.R
import android.os.Bundle;
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity;
import com.example.appexchance.TelaUsuarioHost
import com.example.appexchance.TelaUsuarioIntercambista
import com.example.appexchance.databinding.ActivityFormLoginBinding
import com.example.appexchance.forms.models.LoginRequest
import com.example.appexchance.forms.models.RespostaDoServidor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FormLogin : AppCompatActivity(), AdapterView.OnItemSelectedListener {


    val binding by lazy {
        ActivityFormLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val spinner : Spinner = findViewById(R.id.select_box)

        ArrayAdapter.createFromResource(this,
            R.array.opcoes_usuario,
            android.R.layout.simple_spinner_item).also { arrayAdapter ->
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            spinner.adapter = arrayAdapter

            spinner.onItemSelectedListener = this
        }


        fun fazerLogin(): Call<RespostaDoServidor> {

            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()


            val loginRequest = LoginRequest(email, senha)
            val apiService = RestClient.create()

            if(spinner.selectedItem.equals("Intercambista")){
                return apiService.login(loginRequest)
            }else{
                return apiService.loginHost(loginRequest)
            }

        }

        binding.buttonAcessar.setOnClickListener {
            val call = fazerLogin()

            call.enqueue(object : Callback<RespostaDoServidor> {
                override fun onResponse(call: Call<RespostaDoServidor>, response: Response<RespostaDoServidor>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@FormLogin, "autenticação feita!!!", Toast.LENGTH_SHORT).show()
                        val resposta = response.body()
                        lateinit var telaUsuario: Any

                        if(spinner.selectedItem.equals("Intercambista")){
                             telaUsuario = Intent(this@FormLogin, TelaUsuarioIntercambista::class.java)
                        }else{
                            telaUsuario = Intent(this@FormLogin, TelaUsuarioHost::class.java)
                        }

                        if (resposta != null) {
                            telaUsuario.putExtra("txt_nome", resposta.nome)
                            telaUsuario.putExtra("txt_email", resposta.email)
                        } else {
                            telaUsuario.putExtra("txt_busca", "Sem resposta válida do servidor")
                        }
                        startActivity(telaUsuario)


                    } else {
                        Toast.makeText(this@FormLogin, "Usuario ou Senha invalidos!!!", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<RespostaDoServidor>, t: Throwable) {
                    Toast.makeText(this@FormLogin, "Erro de rede", Toast.LENGTH_SHORT).show()
                }
            })
        }



    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val selectedItem = p0?.getItemAtPosition(p2)
        // Faça algo com a opção selecionada, se necessário
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        // Lidar com a situação em que nada foi selecionado, se necessário
    }

}