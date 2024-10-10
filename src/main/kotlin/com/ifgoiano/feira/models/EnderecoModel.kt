package com.ifgoiano.feira.models

import com.ifgoiano.feira.database.entities.Pessoa
import java.time.LocalDateTime

data class EnderecoModel(
    val id:Long? = null,
    val cep:String,
    val bairro:String,
    val cidade:String,
    val complemento:String,
    val creationDate: LocalDateTime? = null,
    val updateData: LocalDateTime? = null,
    val funcionario: PessoaModel
)
