package com.ifgoiano.feira.api.response

import com.ifgoiano.feira.enum.FormaPagamento
import com.ifgoiano.feira.enum.StatusCompra
import com.ifgoiano.feira.enum.TipoEntrega
import com.ifgoiano.feira.models.ItensModel
import java.math.BigDecimal
import java.time.LocalDateTime

data class CompraResponse(
    val id:Long,
    val nomeComprador:String,
    val numeroComprador:String,
    val tipoEntrega: TipoEntrega,
    val valorTotal: BigDecimal,
    val formaPagamento: FormaPagamento,
    val mensagem:String,
    val status: StatusCompra,
    val creationDate: LocalDateTime? = null,
    val compraProdutos: List<ItensResponse> = mutableListOf()
)
