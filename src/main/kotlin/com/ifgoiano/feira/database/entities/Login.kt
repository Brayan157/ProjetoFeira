package com.ifgoiano.feira.database.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import com.ifgoiano.feira.enum.Funcao
import com.ifgoiano.feira.enum.StatusLogin
import com.ifgoiano.feira.models.LoginModel
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime

@Entity
@Table(name = "tb_login")
data class Login(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column
    val email:String,
    @Column
    val senha:String,
    @Enumerated(EnumType.STRING)
    @Column
    val status: StatusLogin,
    @Column(name = "creation_date")
    @CreationTimestamp
    val creationDate: LocalDateTime? = null,
    @Column(name = "update_date")
    @UpdateTimestamp
    val updateData: LocalDateTime? = null,
    @OneToOne
    @JoinColumn(name = "funcionario_id")
    @JsonBackReference
    val funcionario: Pessoa
){
    fun toLoginModel() = LoginModel(
        id = id,
        email = email,
        senha = senha,
        status = status,
        creationDate = creationDate,
        updateData = updateData,
        funcionario = funcionario.toPessoaModel()
    )
    companion object{
        fun of (loginModel: LoginModel) = Login(
            id = loginModel.id,
            email = loginModel.email,
            senha = loginModel.senha,
            status = loginModel.status,
            creationDate = loginModel.creationDate,
            updateData = loginModel.updateData,
            funcionario = Pessoa.of(loginModel.funcionario)
        )
    }
}
