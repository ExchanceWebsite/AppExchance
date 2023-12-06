package com.example.appexchance.forms.models

import java.io.Serializable

data class Localidade(
    var idLocalidade: Int,
    var pais: String,
    var cidade: String,
    var endereco: String,
    var cep: String
) : Serializable