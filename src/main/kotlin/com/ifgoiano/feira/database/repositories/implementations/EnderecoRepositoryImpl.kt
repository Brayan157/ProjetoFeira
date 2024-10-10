package com.ifgoiano.feira.database.repositories.implementations

import com.ifgoiano.feira.database.entities.Endereco
import com.ifgoiano.feira.database.entities.Pessoa
import com.ifgoiano.feira.database.repositories.interfaces.EnderecoRepository
import com.ifgoiano.feira.database.repositories.interfaces.PessoaRepository
import com.ifgoiano.feira.database.repositories.stringData.EnderecoSpringJpaRepository
import com.ifgoiano.feira.models.EnderecoModel
import com.ifgoiano.feira.models.PessoaModel
import org.springframework.stereotype.Component

@Component
class EnderecoRepositoryImpl(
    val enderecoJpaRepository: EnderecoSpringJpaRepository
):EnderecoRepository {
    override fun save(enderecoModel: EnderecoModel): EnderecoModel {
        val pessoa = Pessoa.of(enderecoModel.funcionario)
        val endereco = Endereco.of(enderecoModel, pessoa)
        return enderecoJpaRepository.save(endereco).toEnderecoModel()
    }

    override fun findByFuncionario(pessoaModel: PessoaModel): EnderecoModel? {
        val pessoa = Pessoa.of(pessoaModel)
        return enderecoJpaRepository.findByFuncionario(pessoa)?.toEnderecoModel()
    }
}