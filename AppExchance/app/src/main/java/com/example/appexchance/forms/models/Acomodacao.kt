package com.example.appexchance.forms.models

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
)