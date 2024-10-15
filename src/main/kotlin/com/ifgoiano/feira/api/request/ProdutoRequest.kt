package com.ifgoiano.feira.api.request

import com.ifgoiano.feira.enum.CategoriaProduto
import com.ifgoiano.feira.enum.StatusProduto
import com.ifgoiano.feira.enum.TipoVendaProduto
import com.ifgoiano.feira.models.ProdutoModel
import java.math.BigDecimal

data class ProdutoRequest (
    val nomeProduto:String,
    val precoUnitario: BigDecimal,
    val tipoProduto: String,
    val status: String,
    val categoriaProduto: CategoriaProduto
){
    fun toProdutoModel() = ProdutoModel(
        nomeProduto = nomeProduto,
        precoUnitario = precoUnitario,
        tipoProduto = TipoVendaProduto.valueOf(tipoProduto),
        status = StatusProduto.valueOf(status),
        categoriaProduto = categoriaProduto
    )
}