package com.example.appexchance.forms.models

import java.io.Serializable

data class Localidade(
    var idLocalidade: Int,
    var pais: String?,
    var cidade: String?,
    var endereco: String?,
    var cep: String?
) : Serializable {
    constructor(idLocalidade: Int) : this(
        idLocalidade = idLocalidade,
        pais = null,
        cidade = null,
        endereco = null,
        cep = null
    )


    constructor(pais: String?,cidade: String?,endereco: String?,cep: String?) : this(
        idLocalidade = 0,
        pais = pais,
        cidade = cidade,
        endereco = endereco,
        cep = cep
    )
}