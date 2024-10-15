package com.ifgoiano.feira.database.repositories.implementations

import com.ifgoiano.feira.database.entities.Produto
import com.ifgoiano.feira.database.repositories.interfaces.ProdutoRepository
import com.ifgoiano.feira.database.repositories.stringData.ProdutoSpringDataRepository
import com.ifgoiano.feira.enum.CategoriaProduto
import com.ifgoiano.feira.models.ProdutoModel
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class ProdutoRepositoryImpl(
    val produtoJpaRepository: ProdutoSpringDataRepository
):ProdutoRepository {
    override fun save(produtoModel: ProdutoModel): ProdutoModel {
        val produtoEntity = Produto.of(produtoModel)
        return produtoJpaRepository.save(produtoEntity).toProdutoModel()
    }

    override fun findByNomeProduto(nomeProduto: String, pageable: Pageable): Page<ProdutoModel> {
        // Realiza a busca usando o método correto do repositório e retorna a página de produtos
        val produtosPage = produtoJpaRepository.findByNomeProdutoContainingIgnoreCase(nomeProduto, pageable)

        // Verifica se a página de produtos está vazia e lança uma exceção se necessário
        if (produtosPage.isEmpty) {
            throw NotFoundException()
        }

        // Converte a lista de entidades Produto para ProdutoModel
        return produtosPage.map { it.toProdutoModel() }
    }

    override fun findAll(pageable: Pageable): Page<ProdutoModel> {
        return produtoJpaRepository.findAll(pageable).map { it.toProdutoModel() }
    }

    override fun findById(produtoId: Long): ProdutoModel {
        return produtoJpaRepository.findById(produtoId)
            .map { it.toProdutoModel() }
            .orElseThrow{NotFoundException()}
    }

    override fun existsByNomeProduto(nomeProduto: String): Boolean {
        return produtoJpaRepository.existsByNomeProduto(nomeProduto)
    }

    override fun findAllById(produtoIds: List<Long>): List<Produto> {
        return produtoJpaRepository.findAllById(produtoIds)
    }

    override fun findByCategoria(categoria: CategoriaProduto,  pageable: Pageable): Page<ProdutoModel> {
        return produtoJpaRepository.findByCategoria(categoria,  pageable).map { it.toProdutoModel() }
    }

}