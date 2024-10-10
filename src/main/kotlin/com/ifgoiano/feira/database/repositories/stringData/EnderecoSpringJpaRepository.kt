package com.ifgoiano.feira.database.repositories.stringData

import com.ifgoiano.feira.database.entities.Endereco
import com.ifgoiano.feira.database.entities.Pessoa
import org.springframework.data.jpa.repository.JpaRepository

interface EnderecoSpringJpaRepository:JpaRepository<Endereco, Long> {
    fun findByFuncionario(pessoa: Pessoa): Endereco?
}