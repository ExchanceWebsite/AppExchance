package com.example.appexchance.forms.models

import java.io.Serializable

data class Reserva(
    val idReserva: Int,
    val estudante: RespostaDadosIntercambista,
    val entrada: String,
    val saida: String,
    val formaPagamento: String,
    val acomodacao: Acomodacao,
    val host: CadastroHostRequest
) : Serializable {
    constructor(
        estudante: RespostaDadosIntercambista,
        entrada: String,
        saida: String,
        formaPagamento: String,
        acomodacao: Acomodacao,
        host: CadastroHostRequest
    ) : this(
        idReserva = 0,
        estudante = estudante,
        entrada = entrada,
        saida = saida,
        formaPagamento = formaPagamento,
        acomodacao = acomodacao,
        host = host
    )
}