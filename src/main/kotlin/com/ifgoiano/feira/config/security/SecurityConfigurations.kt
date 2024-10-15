package com.ifgoiano.feira.config.security

import com.ifgoiano.feira.enum.Funcao
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.csrf.CsrfFilter

@Configuration
@EnableWebSecurity
class SecurityConfigurations(
    private val authConfig: AuthenticationConfiguration,
    private val securityFilter: SecurityFilter
) {
    @Bean
    fun securityFilterChain(http: HttpSecurity):SecurityFilterChain{
        http.csrf().disable()

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/produto/**").hasAnyRole(Funcao.ADMINISTRADOR.name, Funcao.CADASTRANTE.name)
                .requestMatchers(HttpMethod.PUT, "/produto/**").hasAnyRole(Funcao.ADMINISTRADOR.name, Funcao.CADASTRANTE.name)
                .requestMatchers( "/pessoa/**").hasRole(Funcao.ADMINISTRADOR.name)
                .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/compra/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/compra/**").hasAnyRole(Funcao.ADMINISTRADOR.name, Funcao.CADASTRANTE.name)
                .requestMatchers(HttpMethod.GET, "/compra/**").hasAnyRole(Funcao.ADMINISTRADOR.name, Funcao.CADASTRANTE.name)
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                .anyRequest().permitAll()


        http.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter::class.java)
        return http.build()
    }
    @Bean
    fun authenticationManager(): AuthenticationManager {
        return authConfig.authenticationManager
    }
    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}