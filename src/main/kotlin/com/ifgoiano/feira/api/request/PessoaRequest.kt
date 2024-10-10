package com.ifgoiano.feira.api.request

import com.ifgoiano.feira.enum.Funcao
import java.time.LocalDate
import java.time.LocalDateTime

data class PessoaRequest(
    val nome:String,
    val cpf:String,
    val dataNascimento: LocalDate,
    val telefones:String,
    val email:String,
    val senha:String
)
