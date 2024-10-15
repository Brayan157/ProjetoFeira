package com.ifgoiano.feira.database.repositories.interfaces

import com.ifgoiano.feira.database.entities.Produto
import com.ifgoiano.feira.enum.CategoriaProduto
import com.ifgoiano.feira.models.ProdutoModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ProdutoRepository {
    fun save(produtoModel: ProdutoModel): ProdutoModel
    fun findByNomeProduto(nomeProduto: String, pageable: Pageable): Page<ProdutoModel>
    fun findAll(pageable: Pageable): Page<ProdutoModel>
    fun findById(produtoId: Long): ProdutoModel
    fun existsByNomeProduto(nomeProduto: String): Boolean
    fun findAllById(produtoIds: List<Long>): List<Produto>
    fun findByCategoria(categoria: CategoriaProduto,  pageable: Pageable): Page<ProdutoModel>
}