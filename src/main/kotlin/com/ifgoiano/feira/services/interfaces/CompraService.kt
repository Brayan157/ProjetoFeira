package com.ifgoiano.feira.services.interfaces

import com.ifgoiano.feira.api.request.CompraRequest
import com.ifgoiano.feira.api.response.CompraResponse
import com.ifgoiano.feira.enum.StatusCompra

interface CompraService {
    fun save(compraRequest: CompraRequest): CompraResponse
    fun findAll(): List<CompraResponse>
    fun findById(id:Long):CompraResponse
    fun mudarStatusCompra(id: Long, status: StatusCompra): CompraResponse
    fun listarComprasPendentes(status: StatusCompra): List<CompraResponse>
}