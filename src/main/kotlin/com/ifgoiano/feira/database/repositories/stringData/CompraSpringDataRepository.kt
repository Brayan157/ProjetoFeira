package com.ifgoiano.feira.database.repositories.stringData

import com.ifgoiano.feira.database.entities.Compra
import com.ifgoiano.feira.enum.StatusCompra
import org.springframework.data.jpa.repository.JpaRepository

interface CompraSpringDataRepository:JpaRepository<Compra, Long> {
    fun findByStatus(status:StatusCompra): List<Compra>
}