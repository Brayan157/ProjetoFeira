package com.ifgoiano.feira.database.entities

import com.ifgoiano.feira.enum.FormaPagamento
import com.ifgoiano.feira.enum.StatusCompra
import com.ifgoiano.feira.enum.TipoEntrega
import com.ifgoiano.feira.models.CompraModel
import com.ifgoiano.feira.models.ProdutoModel
import com.ifgoiano.feira.services.interfaces.ProdutoService
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "tb_compra")
data class Compra(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long? = null,
    @Column(name = "nome_comprador")
    val nomeComprador:String,
    @Column(name = "numero_comprador")
    val numeroComprador:String,
    @Column(name = "tipo_entrega")
    val tipoEntrega: TipoEntrega,
    @Column(name = "valor_total")
    val valorTotal: BigDecimal,
    @Enumerated(EnumType.STRING)
    @Column(name = "forma_pagamento",  nullable = false)
    val formaPagamento: FormaPagamento,
    @Column(name = "mensagem")
    val mensagem:String,
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    val status: StatusCompra = StatusCompra.PENDENTE,
    @Column(name = "creation_date")
    @CreationTimestamp
    val creationDate: LocalDateTime?,

    @OneToMany(mappedBy = "compra", cascade = [CascadeType.ALL], orphanRemoval = true)
    val compraProdutos: List<Itens> = mutableListOf()
){
    fun toCompraModel() = CompraModel(
        id = id,
        nomeComprador = nomeComprador,
        numeroComprador = numeroComprador,
        tipoEntrega = tipoEntrega,
        valorTotal = valorTotal,
        formaPagamento = formaPagamento,
        mensagem = mensagem,
        status = status,
        creationDate = creationDate,
        compraProdutos = compraProdutos.map { it.toItensModel() }
    )
    companion object {
        fun of(compraModel: CompraModel, produtos: Map<Long, Produto>):Compra{
            val compra = Compra (
                id = compraModel.id,
                nomeComprador = compraModel.nomeComprador,
                numeroComprador = compraModel.numeroComprador,
                tipoEntrega = compraModel.tipoEntrega,
                valorTotal = compraModel.valorTotal,
                formaPagamento = compraModel.formaPagamento,
                mensagem = compraModel.mensagem,
                status = compraModel.status,
                creationDate = compraModel.creationDate
            )

            val compraProdutos = compraModel.compraProdutos.map { itemModel ->
                val produto = produtos[itemModel.produtoId] ?: throw NotFoundException()
                Itens(
                    id = CompraProdutoId(compraId = compraModel.id, produtoId = itemModel.produtoId),
                    compra = compra,  // Associação da compra com o item
                    produto = produto, // Produto encontrado no mapa
                    quantidade = itemModel.quantidade
                )
            }

            // Retorna a compra com os itens corretamente associados
            return compra.copy(compraProdutos = compraProdutos)
        }
    }

}
