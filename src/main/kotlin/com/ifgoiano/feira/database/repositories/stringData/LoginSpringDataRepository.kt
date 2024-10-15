package com.ifgoiano.feira.database.repositories.stringData

import com.ifgoiano.feira.database.entities.Login
import com.ifgoiano.feira.database.entities.Pessoa
import com.ifgoiano.feira.enum.StatusLogin
import com.ifgoiano.feira.models.LoginModel
import org.springframework.data.jpa.repository.JpaRepository

interface LoginSpringDataRepository:JpaRepository<Login, Long> {
    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): Login
    fun findByFuncionario(pessoaEntity: Pessoa): Login
    fun existsByEmailAndStatus(email: String, status: StatusLogin): Boolean
}