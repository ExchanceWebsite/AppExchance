package com.example.appexchance.forms.models

import java.io.Serializable

data class CadastroHostRequest (
    val idHostFamily: Int,
    val nome: String,
    val verificado: String,
    val descricao: String,
    val email: String,
    val senha: String,
    val localidade: Localidade,
    val telefone:String
 ) : Serializable


//"idHostFamily": 1,
//"nome": "Familia Silva",
//"verificado": "verificado por exchance",
//"descricao": "Familia acolhedora e muito simpatica",
//"email": "silva@gmail.com",
//"senha": "$2a$10$K39ke.dVE7aOREzZZ61mg.2eEx5siKhie93F.s28.8eXSiUuqLwpC",