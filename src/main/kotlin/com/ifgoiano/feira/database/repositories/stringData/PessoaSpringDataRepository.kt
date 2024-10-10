package com.ifgoiano.feira.database.repositories.stringData

import com.ifgoiano.feira.database.entities.Pessoa
import org.springframework.data.jpa.repository.JpaRepository

interface PessoaSpringDataRepository: JpaRepository<Pessoa, Long> {
    fun existsByCpf(cpf: String): Boolean
}