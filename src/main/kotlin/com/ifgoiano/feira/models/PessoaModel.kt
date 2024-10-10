package com.ifgoiano.feira.models

import com.ifgoiano.feira.database.entities.Endereco
import com.ifgoiano.feira.database.entities.Login
import com.ifgoiano.feira.enum.Funcao

import java.time.LocalDate
import java.time.LocalDateTime

data class PessoaModel(
    val id: Long? = null,
    val nome:String,
    val cpf:String,
    val dataNascimento: LocalDate,
    val funcao: Funcao,
    val creationDate: LocalDateTime? = null,
    val updateData: LocalDateTime? = null,
    val login: Login? = null,
    val telefones:String,
    val endereco: Endereco? = null
)
