package com.example.appexchance.forms

import com.example.appexchance.forms.models.Acomodacao
import com.example.appexchance.forms.models.CadastroHostRequest
import com.example.appexchance.forms.models.CadastroInterRequest
import com.example.appexchance.forms.models.LoginRequest
import com.example.appexchance.forms.models.Reserva
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
    fun cadastroHost(@Body cadastroHostRequest: CadastroHostRequest ): Call<Void>

    @POST("/estudantes")
    fun cadastro(@Body cadastroInterRequest: CadastroInterRequest): Call<Void>

    @POST("/reservas")
    fun cadastroReserva(@Body reserva: Reserva): Call<Unit>

    @GET("/estudantes/estudante")
    fun buscar(@Query("emai") emai: String, @Query("nome") nome: String): Call<RespostaDadosIntercambista>

    @GET("/acomodacoes/acomodacoes-pais-disponivel")
    fun buscarAcomodacoesPais(@Query("Pais") pais: String, @Query("entrada") entrada: String, @Query("saida") saida: String ): Call<List<Acomodacao>>

    @GET("/acomodacoes/acomodacoes-host")
    fun buscarAcomodacoesHost(@Query("idHost") idHost: Int): Call<List<Acomodacao>>

}