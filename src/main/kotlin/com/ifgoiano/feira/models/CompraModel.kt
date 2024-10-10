package com.ifgoiano.feira.models

import com.ifgoiano.feira.database.entities.Itens
import com.ifgoiano.feira.enum.FormaPagamento
import com.ifgoiano.feira.enum.StatusCompra
import com.ifgoiano.feira.enum.TipoEntrega
import java.math.BigDecimal
import java.time.LocalDateTime

data class CompraModel(
    val id:Long? = null,
    val nomeComprador:String,
    val numeroComprador:String,
    val tipoEntrega: TipoEntrega,
    val valorTotal: BigDecimal,
    val formaPagamento: FormaPagamento,
    val mensagem:String,
    val status: StatusCompra,
    val creationDate: LocalDateTime? = null,
    val compraProdutos: List<ItensModel> = mutableListOf()
){
    fun withProdutos(produtos: List<ItensModel>, valorTotal: BigDecimal)
            = this.copy(compraProdutos = produtos, valorTotal = valorTotal)
}
