package com.ifgoiano.feira.models

import com.ifgoiano.feira.database.entities.Pessoa
import com.ifgoiano.feira.enum.StatusLogin
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime

data class LoginModel(
    val id: Long? = null,
    val email:String,
    val senha:String,
    val status: StatusLogin,
    val creationDate: LocalDateTime? = null,
    val updateData: LocalDateTime? = null,
    val funcionario: PessoaModel
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        println("ROLE_${funcionario.funcao.name}")
        return mutableListOf(SimpleGrantedAuthority("ROLE_${funcionario.funcao.name}"))
    }

    override fun getPassword(): String {
        return senha
    }

    override fun getUsername(): String {
        return email
    }

}
