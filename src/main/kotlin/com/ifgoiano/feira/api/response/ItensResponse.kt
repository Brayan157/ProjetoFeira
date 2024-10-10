package com.ifgoiano.feira.api.response

import com.ifgoiano.feira.models.ProdutoModel

data class ItensResponse(
    val produtoModel: ProdutoModel,
    val quantidade: Double
)
