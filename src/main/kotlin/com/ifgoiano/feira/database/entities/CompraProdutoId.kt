package com.ifgoiano.feira.database.entities

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class CompraProdutoId(
    @Column(name = "compra_id")
    val compraId:Long? = null,
    @Column(name = "produto_id")
    val produtoId:Long? = null
): Serializable
