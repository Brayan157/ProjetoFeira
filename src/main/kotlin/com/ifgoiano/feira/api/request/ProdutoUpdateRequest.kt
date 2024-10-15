package com.ifgoiano.feira.api.request

import com.ifgoiano.feira.enum.CategoriaProduto
import java.math.BigDecimal

data class ProdutoUpdateRequest(
    val nomeProduto:String? = null,
    val precoUnitario: BigDecimal?= null,
    val tipoProduto: String?= null,
    val status: String?= null,
    val categoriaProduto: CategoriaProduto? = null
)
