package com.ifgoiano.feira.database.repositories.stringData

import com.ifgoiano.feira.database.entities.Produto
import com.ifgoiano.feira.enum.CategoriaProduto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface ProdutoSpringDataRepository : JpaRepository<Produto, Long>{
    fun findByNomeProduto(nomeProduto:String):Produto?
    fun existsByNomeProduto(nomeProduto: String): Boolean
    fun findByNomeProdutoContainingIgnoreCase(nomeProduto: String, pageable: Pageable): Page<Produto>
    fun findByCategoria(categoria: CategoriaProduto,  pageable: Pageable): Page<Produto>
}