package com.ifgoiano.feira.database.repositories.interfaces

import com.ifgoiano.feira.models.EnderecoModel
import com.ifgoiano.feira.models.PessoaModel

interface EnderecoRepository {
    fun save(enderecoModel: EnderecoModel): EnderecoModel
    fun findByFuncionario(pessoaModel: PessoaModel):EnderecoModel?
}