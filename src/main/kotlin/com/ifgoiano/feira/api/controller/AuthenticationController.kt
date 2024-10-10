package com.ifgoiano.feira.api.controller
import com.ifgoiano.feira.api.request.AuthenticationRequest
import com.ifgoiano.feira.api.request.NovaSenhaRequest
import com.ifgoiano.feira.config.security.TokenService
import com.ifgoiano.feira.models.LoginModel
import com.ifgoiano.feira.services.interfaces.PessoaService
import jakarta.servlet.http.HttpServletRequest
import org.apache.catalina.User
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthenticationController(
    val authenticationManager: AuthenticationManager,
    val tokenService: TokenService,
    val pessoaService: PessoaService,
) {
    @PostMapping("/login")
    fun login(@RequestBody login: AuthenticationRequest): ResponseEntity<Any>{
        return try {
            val loginPassword = UsernamePasswordAuthenticationToken(login.email, login.senha)
            val auth = this.authenticationManager.authenticate(loginPassword)
            val token = tokenService.generateToken((auth.principal) as LoginModel)
            ResponseEntity.ok(token)
        } catch (ex: Exception){
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("E-mail ou senha incorreto")
        }
    }

    @PutMapping("/editar-senha")
    fun editarSenha(@RequestBody editarSenhaRequest: NovaSenhaRequest, request: HttpServletRequest): ResponseEntity<String> {
        val token = tokenService.recoverToken(request) ?: throw RuntimeException("Token inválido ou ausente")
        val email = tokenService.validateToken(token)

        pessoaService.mudarSenha(email, editarSenhaRequest)

        return ResponseEntity.ok("Senha atualizada com sucesso")
    }

}