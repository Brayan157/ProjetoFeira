package com.ifgoiano.feira.services.interfaces

import com.ifgoiano.feira.api.request.NovaSenhaRequest
import com.ifgoiano.feira.api.request.PessoaRequest
import com.ifgoiano.feira.api.request.PessoaUpdateRequest
import com.ifgoiano.feira.api.response.PessoaResponse
import com.ifgoiano.feira.models.PessoaModel
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UserDetails

interface PessoaService {
    fun save(pessoaRequest: PessoaRequest):PessoaModel
    fun findById(id: Long): PessoaModel
    fun findAll(): List<PessoaModel>
    fun editar(email:String, pessoaUpdateRequest: PessoaUpdateRequest): PessoaModel
    fun mudarSenha(email:String, novaSenha: NovaSenhaRequest): ResponseEntity<Any>
    fun loadUserByUsername(emailUsuario: String): UserDetails
    fun loginStatusActive(email: String):Boolean
    fun desativarLogin(id: Long): PessoaModel
    fun tornarAdmin(id: Long): PessoaModel
}