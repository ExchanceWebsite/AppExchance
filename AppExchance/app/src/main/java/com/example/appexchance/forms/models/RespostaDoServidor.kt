package com.example.appexchance.forms.models

data class RespostaDoServidor(
    val idEstudante: Int, // ID do estudante
    val nome: String, // Nome do estudante
    val email: String, // Email do estudante
    val token: String // Token de autenticação
)


