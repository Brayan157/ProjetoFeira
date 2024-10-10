package com.ifgoiano.feira.database.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import com.ifgoiano.feira.models.EnderecoModel
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "tb_endereco")
data class Endereco (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // TODO SE FOR MANTER IDENTITY, É BOM DAR UMA ESTUDADA EM COMO FUNCIONA PRA FINS EDUCATIVOS
    val id:Long? = null,
    @Column
    val cep:String,
    @Column
    val bairro:String,
    @Column
    val cidade:String,
    @Column
    val complemento:String,
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
    fun toEnderecoModel() = EnderecoModel(
        id = id,
        cep = cep,
        bairro = bairro,
        cidade = cidade,
        complemento = complemento,
        creationDate = creationDate,
        updateData = updateData,
        funcionario = funcionario.toPessoaModel()
    )
    companion object {
        fun of(enderecoModel: EnderecoModel, funcionario: Pessoa) = Endereco(
            id = enderecoModel.id,
            cep = enderecoModel.cep,
            bairro = enderecoModel.bairro,
            cidade = enderecoModel.cidade,
            complemento = enderecoModel.complemento,
            creationDate = enderecoModel.creationDate,
            updateData = enderecoModel.updateData,
            funcionario = funcionario
        )
    }
}