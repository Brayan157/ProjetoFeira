package com.ifgoiano.feira.api.controller

import com.ifgoiano.feira.api.request.EnderecoRequest
import com.ifgoiano.feira.api.request.EnderecoUpdateRequest
import com.ifgoiano.feira.config.security.TokenService
import com.ifgoiano.feira.models.EnderecoModel
import com.ifgoiano.feira.services.interfaces.EnderecoService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/endereco")
class EnderecoController(
    val enderecoService: EnderecoService,
    val authenticationManager: AuthenticationManager,
    val tokenService: TokenService
) {
    @PostMapping
    fun salvar(@RequestBody endereco: EnderecoRequest, request: HttpServletRequest):EnderecoModel{
        val token = tokenService.recoverToken(request) ?: throw RuntimeException("Token inválido ou ausente")
        val email = tokenService.validateToken(token)
        return enderecoService.salvar(endereco, email)
    }
    @PutMapping
    fun editar(@RequestBody endereco: EnderecoUpdateRequest, request: HttpServletRequest):EnderecoModel{
        val token = tokenService.recoverToken(request) ?: throw RuntimeException("Token inválido ou ausente")
        val email = tokenService.validateToken(token)
        return enderecoService.editar(endereco, email)
    }

}
