package com.example.appexchance.forms.models

data class Reserva(
    val idReserva: Int,
    val estudante: RespostaDadosIntercambista,
    val entrada: String,
    val saida: String,
    val formaPagamento: String,
    val host: CadastroHostRequest
)