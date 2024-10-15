package com.ifgoiano.feira.database.repositories.interfaces

import com.ifgoiano.feira.enum.StatusLogin
import com.ifgoiano.feira.models.LoginModel
import com.ifgoiano.feira.models.PessoaModel

interface LoginRepository {
    fun existsByEmail(email:String):Boolean
    fun findByEmail(email: String):LoginModel
    fun save(login: LoginModel):LoginModel
    fun findByFuncionario(pessoa: PessoaModel): LoginModel
    fun existsByEmailAndStatus(email: String, ativo: StatusLogin): Boolean
}