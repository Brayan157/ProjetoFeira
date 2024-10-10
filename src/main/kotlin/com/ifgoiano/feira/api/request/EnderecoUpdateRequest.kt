package com.ifgoiano.feira.api.request

data class EnderecoUpdateRequest(
    val cep:String? = null,
    val bairro:String? = null,
    val cidade:String? = null,
    val complemento:String?= null
)
