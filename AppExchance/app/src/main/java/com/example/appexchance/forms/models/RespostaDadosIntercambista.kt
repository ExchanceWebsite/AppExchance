package com.example.appexchance.forms.models

data class RespostaDadosIntercambista (
    val idEstudante: Int,
    val nome: String?,
    val idade: Int?,
    val descricao: String?,
    val email: String?,
    val senha: String?,
    val telefone: String?,
    val cpf: String?,
    val localidade: Localidade?
) {
    constructor(idEstudante: Int) : this(
        idEstudante = idEstudante,
        nome = null,
        idade = null,
        descricao = null,
        email = null,
        senha = null,
        telefone = null,
        cpf = null,
        localidade = null
    )
}


