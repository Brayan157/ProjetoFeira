package com.ifgoiano.feira.database.repositories.implementations

import com.ifgoiano.feira.database.entities.Compra
import com.ifgoiano.feira.database.repositories.interfaces.CompraRepository
import com.ifgoiano.feira.database.repositories.interfaces.ProdutoRepository
import com.ifgoiano.feira.database.repositories.stringData.CompraSpringDataRepository
import com.ifgoiano.feira.enum.StatusCompra
import com.ifgoiano.feira.models.CompraModel
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Component

@Component
class CompraRepositoryImpl(
    val compraJpaRepository:CompraSpringDataRepository,
    val produtoRepository:ProdutoRepository
):CompraRepository {
    override fun save(compraFinal: CompraModel): CompraModel {
        val produtoIds = compraFinal.compraProdutos.map { it.produtoId }
        val produtos = produtoRepository.findAllById(produtoIds).associateBy { it.id!! }
        val compra = Compra.of(compraFinal, produtos)
        return compraJpaRepository.save(compra).toCompraModel()
    }

    override fun findAll(): List<CompraModel> = compraJpaRepository.findAll().map { it.toCompraModel() }
    override fun findById(id: Long): CompraModel {
        return compraJpaRepository.findById(id)
            .map { it.toCompraModel() }
            .orElseThrow { NotFoundException() }
    }

    override fun findByStatus(status: StatusCompra): List<CompraModel> {
        return compraJpaRepository.findByStatus(status).map{ it.toCompraModel()}
    }
}