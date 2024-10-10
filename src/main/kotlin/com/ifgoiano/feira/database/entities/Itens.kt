package com.ifgoiano.feira.database.entities

import com.ifgoiano.feira.models.ItensModel
import jakarta.persistence.Column
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(name = "tb_itens")
data class Itens(
    @EmbeddedId
    private val id: CompraProdutoId = CompraProdutoId(),

    @ManyToOne
    @MapsId("compraId")
    @JoinColumn(name = "compra_id")
    val compra: Compra,

    @ManyToOne
    @MapsId("produtoId")
    @JoinColumn(name = "produto_Id")
    val produto: Produto,
    @Column(name = "quantidade")
    val quantidade:Double
){
    fun toItensModel() = ItensModel(
        compraId = compra.id!!,
        produtoId = produto.id!!,
        quantidade = quantidade
    )

}
