package com.ifgoiano.feira.services.interfaces

import com.ifgoiano.feira.api.request.ProdutoRequest
import com.ifgoiano.feira.api.request.ProdutoUpdateRequest
import com.ifgoiano.feira.enum.CategoriaProduto
import com.ifgoiano.feira.models.ProdutoModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ProdutoService {
    fun save(produtoRequest: ProdutoRequest): ProdutoModel
    fun findByNomeProduto(nomeProduto: String, pageable: Pageable): Page<ProdutoModel>
    fun findAll(pageable: Pageable): Page<ProdutoModel>
    fun findById(produtoId: Long): ProdutoModel
    fun update(produtoRequest: ProdutoUpdateRequest, produtoId: Long): ProdutoModel
    fun existsByNomeProduto(nomeProduto: String):Boolean
    fun findByCategoria(categoria: CategoriaProduto,  pageable: Pageable): Page<ProdutoModel>
}