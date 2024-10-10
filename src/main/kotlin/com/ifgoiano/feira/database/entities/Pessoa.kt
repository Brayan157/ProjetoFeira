package com.ifgoiano.feira.database.entities

import com.fasterxml.jackson.annotation.JsonManagedReference
import com.ifgoiano.feira.enum.Funcao
import com.ifgoiano.feira.models.PessoaModel
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "tb_pessoa")
data class Pessoa(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column // TODO SE NÃO FOR TER NENHUM PARAMETRO, ESSA ANOTAÇÃO NÃO É NECESSÁRIA EM NENHUM DOS CAMPOS
    val nome:String,
    @Column
    val cpf:String,
    @Column(name = "data_nascimento")
    val dataNascimento:LocalDate,
    @Enumerated(EnumType.STRING)
    @Column
    val funcao: Funcao,
    @Column
    val telefone:String,
    @Column(name = "creation_date")
    @CreationTimestamp
    val creationDate:LocalDateTime? = null,
    @Column(name = "update_date")
    @UpdateTimestamp
    val updateData:LocalDateTime? = null,
    @OneToOne(mappedBy = "funcionario")
    @JsonManagedReference // Para evitar a serialização bidirecional
    val login: Login? = null,
    @OneToOne(mappedBy = "funcionario")
    @JsonManagedReference
    val endereco: Endereco? = null
){
    fun toPessoaModel() = PessoaModel(
        id = id,
        nome = nome,
        cpf = cpf,
        dataNascimento = dataNascimento,
        funcao = funcao,
        telefones = telefone,
        creationDate = creationDate,
        updateData = updateData,
        login = login,
        endereco = endereco
    )
    companion object{
        fun of (pessoaModel: PessoaModel) = Pessoa(
            id = pessoaModel.id,
            nome = pessoaModel.nome,
            cpf = pessoaModel.cpf,
            dataNascimento = pessoaModel.dataNascimento,
            funcao = pessoaModel.funcao,
            telefone = pessoaModel.telefones,
            creationDate = pessoaModel.creationDate,
            updateData = pessoaModel.updateData,
            login = pessoaModel.login,
            endereco = pessoaModel.endereco
        )
    }
}