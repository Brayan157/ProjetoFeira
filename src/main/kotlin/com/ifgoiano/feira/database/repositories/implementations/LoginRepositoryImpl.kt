package com.ifgoiano.feira.database.repositories.implementations

import com.ifgoiano.feira.database.entities.Login
import com.ifgoiano.feira.database.entities.Pessoa
import com.ifgoiano.feira.database.repositories.interfaces.LoginRepository
import com.ifgoiano.feira.database.repositories.stringData.LoginSpringDataRepository
import com.ifgoiano.feira.models.LoginModel
import com.ifgoiano.feira.models.PessoaModel
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Component

@Component
class LoginRepositoryImpl(
    val loginJpaRepository: LoginSpringDataRepository
):LoginRepository {
    override fun existsByEmail(email: String): Boolean {
        return loginJpaRepository.existsByEmail(email)
    }

    override fun findByEmail(email: String): LoginModel {
        return loginJpaRepository.findByEmail(email).toLoginModel()
    }

    override fun save(login: LoginModel): LoginModel {
        val loginEntity = Login.of(login)
        return loginJpaRepository.save(loginEntity).toLoginModel()
    }

    override fun findByFuncionario(pessoa: PessoaModel): LoginModel {
        val pessoaEntity = Pessoa.of(pessoa)
        return loginJpaRepository.findByFuncionario(pessoaEntity).toLoginModel()
    }
}