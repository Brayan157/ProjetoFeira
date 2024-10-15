package com.ifgoiano.feira.services.implementations

import com.ifgoiano.feira.api.request.ProdutoRequest
import com.ifgoiano.feira.api.request.ProdutoUpdateRequest
import com.ifgoiano.feira.database.repositories.interfaces.ProdutoRepository
import com.ifgoiano.feira.enum.CategoriaProduto
import com.ifgoiano.feira.enum.StatusProduto
import com.ifgoiano.feira.enum.TipoVendaProduto
import com.ifgoiano.feira.models.ProdutoModel
import com.ifgoiano.feira.services.interfaces.ProdutoService
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import kotlin.IllegalArgumentException

@Service
class ProdutoServiceImpl(
    val produtoRepository: ProdutoRepository
):ProdutoService{
    override fun save(produtoRequest: ProdutoRequest): ProdutoModel {
        val produtoModel:ProdutoModel = produtoRequest.toProdutoModel()
        if (!existsByNomeProduto(produtoRequest.nomeProduto)) {
            return produtoRepository.save(produtoModel)
        }
        throw NotFoundException()
    }

    override fun findByNomeProduto(nomeProduto: String, pageable: Pageable): Page<ProdutoModel> {
        return produtoRepository.findByNomeProduto(nomeProduto, pageable)
    }

    override fun findAll(pageable: Pageable): Page<ProdutoModel> {
        return produtoRepository.findAll(pageable)
    }

    override fun findById(produtoId: Long): ProdutoModel {
        return produtoRepository.findById(produtoId)
    }

    override fun update(produtoRequest: ProdutoUpdateRequest, produtoId: Long): ProdutoModel {
        val produtoModel = findById(produtoId)
        val produtoToSave = produtoModel.copy(
            nomeProduto =produtoRequest.nomeProduto ?:produtoModel.nomeProduto,
            precoUnitario = produtoRequest.precoUnitario ?: produtoModel.precoUnitario,
            tipoProduto = produtoRequest.tipoProduto?.let { TipoVendaProduto.valueOf(it) } ?: produtoModel.tipoProduto,
            status = produtoRequest.status?.let { StatusProduto.valueOf(it) } ?: produtoModel.status,
            categoriaProduto = produtoRequest.categoriaProduto?.let { CategoriaProduto.valueOf(it.toString()) } ?: produtoModel.categoriaProduto
        )
        return produtoRepository.save(produtoToSave)
    }

    override fun existsByNomeProduto(nomeProduto: String): Boolean {
        return produtoRepository.existsByNomeProduto(nomeProduto)
    }

    override fun findByCategoria(categoria: CategoriaProduto,  pageable: Pageable): Page<ProdutoModel> {

        return produtoRepository.findByCategoria(categoria,  pageable)
    }

}