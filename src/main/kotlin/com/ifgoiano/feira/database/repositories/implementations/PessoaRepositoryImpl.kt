package com.ifgoiano.feira.database.repositories.implementations

import com.ifgoiano.feira.database.entities.Pessoa
import com.ifgoiano.feira.database.repositories.interfaces.PessoaRepository
import com.ifgoiano.feira.database.repositories.stringData.PessoaSpringDataRepository
import com.ifgoiano.feira.models.PessoaModel
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Component

@Component
class PessoaRepositoryImpl(
    val pessoaJpaRepository: PessoaSpringDataRepository
):PessoaRepository {
    override fun save(pessoaModel: PessoaModel): PessoaModel {
        val pessoa = Pessoa.of(pessoaModel)
        return pessoaJpaRepository.save(pessoa).toPessoaModel()
    }

    override fun findById(id: Long): PessoaModel {
        return pessoaJpaRepository.findById(id)
            .map { it.toPessoaModel() }
            .orElseThrow { NotFoundException() }
    }

    override fun existsByCpf(cpf: String): Boolean {
        return pessoaJpaRepository.existsByCpf(cpf)
    }

    override fun findAll(): List<PessoaModel> {
        return pessoaJpaRepository.findAll().map { it.toPessoaModel() }
    }
}