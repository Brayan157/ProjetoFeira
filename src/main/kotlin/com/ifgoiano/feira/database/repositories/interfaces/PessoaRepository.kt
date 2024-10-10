package com.ifgoiano.feira.database.repositories.interfaces

import com.ifgoiano.feira.models.PessoaModel

interface PessoaRepository {
   fun save(pessoaModel: PessoaModel): PessoaModel
   fun findById(id: Long): PessoaModel
   fun existsByCpf(cpf: String): Boolean
   fun findAll(): List<PessoaModel>

}