package com.example.appexchance.forms

import com.example.appexchance.forms.models.CadastroHostRequest
import com.example.appexchance.forms.models.CadastroInterRequest
import com.example.appexchance.forms.models.LoginRequest
import com.example.appexchance.forms.models.RespostaDadosIntercambista
import com.example.appexchance.forms.models.RespostaDoServidor
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("/estudantes/login")
    fun login(@Body loginRequest: LoginRequest): Call<RespostaDoServidor>

    @POST("/hosts/login")
    fun loginHost(@Body loginRequest: LoginRequest): Call<RespostaDoServidor>

    @POST("/hosts")
    fun cadastroHost(@Body cadastroHostRequest: CadastroHostRequest )

    @POST("/estudantes")
    fun cadastro(@Body cadastroInterRequest: CadastroInterRequest): Call<Void>


    @GET("/estudantes/estudante")
    fun buscar(@Query("emai") emai: String, @Query("nome") nome: String): Call<RespostaDadosIntercambista>

}
