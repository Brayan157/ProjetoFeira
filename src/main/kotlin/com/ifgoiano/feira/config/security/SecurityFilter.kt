package com.ifgoiano.feira.config.security

import com.ifgoiano.feira.database.repositories.interfaces.LoginRepository
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class SecurityFilter(
    val tokenService: TokenService,
    val loginRepository: LoginRepository
): OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val token = this.recoverToken(request)
        if (token != null){
            val login = tokenService.validateToken(token);
            val user:UserDetails = loginRepository.findByEmail(login)
            val authentication = UsernamePasswordAuthenticationToken(user, null, user.authorities)
            SecurityContextHolder.getContext().authentication = authentication
        }
        filterChain.doFilter(request, response)
    }
    private fun recoverToken(request: HttpServletRequest): String? {
        val authHeader = request.getHeader("Authorization") // corrigido aqui
        if (authHeader == null || !authHeader.startsWith("Bearer ")) return null
        return authHeader.replace("Bearer ", "").trim()
    }

}