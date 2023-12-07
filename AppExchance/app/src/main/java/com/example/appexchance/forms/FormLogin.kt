package com.example.appexchance.forms

import RestClient
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appexchance.R
import com.example.appexchance.TelaPrincipal
import com.example.appexchance.TelaPrincipalHost
import com.example.appexchance.databinding.ActivityFormLoginBinding
import com.example.appexchance.forms.models.LoginRequest
import com.example.appexchance.forms.models.RespostaDoServidor
import com.example.appexchance.utils.SharedPrefsManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FormLogin : AppCompatActivity() {

    val binding by lazy {
        ActivityFormLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupDropDown()
        setupButton()
    }

    private fun setupDropDown() {
        ArrayAdapter
            .createFromResource(this, R.array.opcoes_usuario, android.R.layout.simple_spinner_item)
            .also { arrayAdapter ->
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.selectBox.apply {
                    adapter = arrayAdapter
                    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            p: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {}

                        override fun onNothingSelected(parent: AdapterView<*>?) {}
                    }
                }
            }
    }

    private fun doLogin(): Call<RespostaDoServidor> {
        val email = binding.editEmail.text.toString()
        val senha = binding.editSenha.text.toString()

        val loginRequest = LoginRequest(email, senha)
        val apiService = RestClient.create()

        return if (binding.selectBox.selectedItem.equals("Intercambista"))
            apiService.login(loginRequest)
        else
            apiService.loginHost(loginRequest)
    }

    private fun setupButton() {
        binding.buttonAcessar.setOnClickListener {
            requestLogin()
        }
    }

    private fun requestLogin() {
        doLogin().enqueue(object : Callback<RespostaDoServidor> {
            override fun onResponse(
                call: Call<RespostaDoServidor>,
                response: Response<RespostaDoServidor>
            ) {
                if (response.isSuccessful) {
                    Toast.makeText(this@FormLogin, "autenticação feita!!!", Toast.LENGTH_SHORT)
                        .show()

                    val telaUsuario =
                        if (binding.selectBox.selectedItem.equals("Intercambista")) {
                            Intent(this@FormLogin, TelaPrincipal::class.java)
                        } else {
                            Intent(this@FormLogin, TelaPrincipalHost::class.java)
                        }

                    response.body()?.let {
                        SharedPrefsManager(this@FormLogin).saveInfo(it)
                        startActivity(telaUsuario)
                    }
                } else {
                    Toast.makeText(
                        this@FormLogin,
                        "Usuario ou Senha invalidos!!!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<RespostaDoServidor>, t: Throwable) {
                Toast.makeText(this@FormLogin, "Erro de rede", Toast.LENGTH_SHORT).show()
            }
        })
    }
}