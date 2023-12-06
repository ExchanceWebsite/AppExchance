package com.example.appexchance.forms.models

import java.io.Serializable

data class Acomodacao(
    val idAcomodacao: Int,
    val host: CadastroHostRequest,
    val localidade: Localidade,
    val reservado: Boolean,
    val reserva: Reserva,
    val descricao: String,
    val inicioDisponibilidade: String,
    val fimDisponibilidade: String,
    val valorDiaria: Int,
    val regras: String
) : Serializable