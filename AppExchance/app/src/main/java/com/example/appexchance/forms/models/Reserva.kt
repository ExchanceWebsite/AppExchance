package com.example.appexchance.forms.models

import java.io.Serializable

data class Reserva(
    val idReserva: Int,
    val estudante: RespostaDadosIntercambista,
    val entrada: String,
    val saida: String,
    val formaPagamento: String,
    val host: CadastroHostRequest
) : Serializable