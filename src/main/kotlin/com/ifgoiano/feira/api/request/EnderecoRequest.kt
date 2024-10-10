package com.ifgoiano.feira.api.request

import com.ifgoiano.feira.database.entities.Pessoa
import java.time.LocalDateTime

data class EnderecoRequest(
    val cep:String,
    val bairro:String,
    val cidade:String,
    val complemento:String
)
