package com.example.appexchance.forms.models

data class CadastroInterRequest(
    val nome: String,
    val idade: Int,
    val descricao: String,
    val email: String,
    val senha: String,
    val telefone: String,
    val cpf: String,
    val localidade: Localidade?
)

//"nome": "string",
//"idade": 13,
//"descricao": "string",
//"email": "string",
//"senha": "stringst",
//"telefone": "string",
//"cpf": "string",
//"localidade": {
//    "idLocalidade": 0,
//    "pais": "string",
//    "cidade": "string",
//    "endereco": "string",
//    "cep": "string"
//}