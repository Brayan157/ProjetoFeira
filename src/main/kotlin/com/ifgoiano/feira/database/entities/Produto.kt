package com.ifgoiano.feira.database.entities

import com.ifgoiano.feira.enum.StatusProduto
import com.ifgoiano.feira.enum.TipoVendaProduto
import com.ifgoiano.feira.models.ProdutoModel
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "tb_produto")
data class Produto(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(name = "nome")
    val nomeProduto:String,
    @Column(name = "preco_unitario")
    val precoUnitario:BigDecimal,
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_venda_produto", nullable = false)
    val tipoProduto:TipoVendaProduto,
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val status: StatusProduto,
    @Column(name = "creation_date")
    @CreationTimestamp
    val creationDate: LocalDateTime? = null,
    @Column(name = "update_date")
    @UpdateTimestamp
    val updateData: LocalDateTime? = null
){
    fun toProdutoModel() = ProdutoModel(
        id = id,
        nomeProduto = nomeProduto,
        precoUnitario = precoUnitario,
        tipoProduto = tipoProduto,
        status = status,
        creationDate = creationDate,
        updateData = updateData
    )

    companion object {
        fun of(produtoModel: ProdutoModel) =Produto (
            id = produtoModel.id,
            nomeProduto = produtoModel.nomeProduto,
            precoUnitario = produtoModel.precoUnitario,
            tipoProduto = produtoModel.tipoProduto,
            status = produtoModel.status,
            creationDate = produtoModel.creationDate,
            updateData = produtoModel.updateData
        )
    }
}
