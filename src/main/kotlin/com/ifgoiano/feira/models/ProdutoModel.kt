package com.ifgoiano.feira.models

import com.ifgoiano.feira.enum.CategoriaProduto
import com.ifgoiano.feira.enum.StatusProduto
import com.ifgoiano.feira.enum.TipoVendaProduto
import java.math.BigDecimal
import java.time.LocalDateTime

data class ProdutoModel(
    val id: Long? = null,
    val nomeProduto:String,
    val precoUnitario: BigDecimal,
    val tipoProduto: TipoVendaProduto,
    val status: StatusProduto,
    val creationDate: LocalDateTime? = null,
    val updateData: LocalDateTime? = null,
    val categoriaProduto: CategoriaProduto
)
