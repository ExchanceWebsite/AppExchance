package com.example.appexchance.forms.models

data class CadastroHostRequest (
    val nome: String,
    val verificado: String,
    val descricao: String,
    val email: String,
    val senha: String,
    val localidade: Localidade
 )


//"idHostFamily": 1,
//"nome": "Familia Silva",
//"verificado": "verificado por exchance",
//"descricao": "Familia acolhedora e muito simpatica",
//"email": "silva@gmail.com",
//"senha": "$2a$10$K39ke.dVE7aOREzZZ61mg.2eEx5siKhie93F.s28.8eXSiUuqLwpC",