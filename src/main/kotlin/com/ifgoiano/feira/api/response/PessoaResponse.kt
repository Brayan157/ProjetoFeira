package com.ifgoiano.feira.api.response

import com.ifgoiano.feira.database.entities.Login
import com.ifgoiano.feira.enum.Funcao
import com.ifgoiano.feira.models.EnderecoModel
import com.ifgoiano.feira.models.LoginModel
import java.time.LocalDate
import java.time.LocalDateTime

data class PessoaResponse(
    val id: Long,
    val nome:String,
    val cpf:String,
    val dataNascimento: LocalDate,
    val funcao: Funcao,
    val creationDate: LocalDateTime? = null,
    val updateData: LocalDateTime? = null,
    val login: LoginModel,
    val telefones:String,
    val enderecoModel: EnderecoModel? = null
)
