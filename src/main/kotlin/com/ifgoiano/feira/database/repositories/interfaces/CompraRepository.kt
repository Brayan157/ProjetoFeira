package com.ifgoiano.feira.database.repositories.interfaces

import com.ifgoiano.feira.enum.StatusCompra
import com.ifgoiano.feira.models.CompraModel

interface CompraRepository {
    fun save(compraFinal: CompraModel): CompraModel
    fun findAll(): List<CompraModel>
    fun findById(id: Long): CompraModel
    fun findByStatus(status: StatusCompra): List<CompraModel>
}