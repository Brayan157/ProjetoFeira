package com.ifgoiano.feira.config.security

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTCreator
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTCreationException
import com.auth0.jwt.exceptions.JWTVerificationException
import com.ifgoiano.feira.database.entities.Login
import com.ifgoiano.feira.models.LoginModel
import com.ifgoiano.feira.models.PessoaModel
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

@Service
class TokenService(
    @Value("\${api.security.token.secret}")
    val secret:String,
    val algorithm: Algorithm = Algorithm.HMAC256(secret),
    val emissor:String = "feira"
) {
    fun generateToken(user: LoginModel):String{
        try {
            val token = JWT.create()
                .withIssuer(emissor)
                .withSubject(user.username)
                .withClaim("role", "ROLE_${user.funcionario.funcao.name}")
                .withExpiresAt(genExpirationDate())
                .sign(algorithm)
            return token
        }catch (exception: JWTCreationException){
            throw RuntimeException("Erro ao gerar o token", exception)
        }
    }
    fun validateToken(token: String): String {
        try {
            return JWT.require(algorithm)
                .withIssuer(emissor)
                .build()
                .verify(token)
                .subject
        } catch (exception: JWTVerificationException) {
            throw RuntimeException("Token inválido ou expirado", exception)
        } catch (exception: Exception) {
            throw RuntimeException("Erro ao validar o token", exception)
        }
    }
    fun recoverToken(request: HttpServletRequest): String? {
        val authHeader = request.getHeader("Authorization") ?: return null
        return authHeader.replace("Bearer ", "").trim()
    }
    fun genExpirationDate():Instant = LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.ofHours(-3)) // TODO mudar pra gmt -3 de acordo com documentação oficial dps
}