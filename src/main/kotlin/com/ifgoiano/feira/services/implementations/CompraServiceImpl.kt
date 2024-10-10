package com.ifgoiano.feira.services.implementations

import com.ifgoiano.feira.api.request.CompraRequest
import com.ifgoiano.feira.api.response.CompraResponse
import com.ifgoiano.feira.api.response.ItensResponse
import com.ifgoiano.feira.database.entities.Compra
import com.ifgoiano.feira.database.repositories.interfaces.CompraRepository
import com.ifgoiano.feira.database.repositories.interfaces.ProdutoRepository
import com.ifgoiano.feira.enum.StatusCompra
import com.ifgoiano.feira.models.CompraModel
import com.ifgoiano.feira.models.ItensModel
import com.ifgoiano.feira.models.ProdutoModel
import com.ifgoiano.feira.services.interfaces.CompraService
import com.ifgoiano.feira.services.interfaces.ProdutoService
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.function.BiFunction

@Service
class CompraServiceImpl(
    val produtoService: ProdutoService,
    val compraRepository: CompraRepository,
    val produtoRepository: ProdutoRepository
):CompraService {
    override fun save(compraRequest: CompraRequest): CompraResponse {
        val compraModel = CompraModel(
            nomeComprador = compraRequest.nomeComprador,
            numeroComprador = compraRequest.numeroComprador,
            tipoEntrega = compraRequest.tipoEntrega,
            valorTotal = BigDecimal.ZERO,
            formaPagamento = compraRequest.formaPagamento,
            mensagem = compraRequest.mensagem,
            status = StatusCompra.PENDENTE
        )
        val compraSalva = compraRepository.save(compraModel)

        val itens = mutableListOf<ItensModel>()
        var total = BigDecimal.ZERO


        for (itemRequest in compraRequest.itens) {
            val produtoModel = produtoService.findById(itemRequest.produtoId)

            val item = ItensModel(
                produtoId = produtoModel.id!!,
                quantidade = itemRequest.quantidade,
                compraId = compraSalva.id!!
            )
            itens.add(item)

            total = total.add(produtoModel.precoUnitario.multiply(BigDecimal(itemRequest.quantidade)))

        }
        val compraFinal = compraSalva.withProdutos(itens, total)

        // Salva a compra e itens no repositório
        val compra =  compraRepository.save(compraFinal)
        val compraProdutos = compra.compraProdutos.map { itensModel ->
            ItensResponse(
                produtoModel = produtoService.findById(itensModel.produtoId),
                quantidade = itensModel.quantidade
            )
        }
        return CompraResponse(
            id = compra.id!!,
            nomeComprador = compra.nomeComprador,
            numeroComprador = compra.numeroComprador,
            tipoEntrega = compra.tipoEntrega,
            valorTotal = compra.valorTotal,
            formaPagamento = compra.formaPagamento,
            mensagem = compra.mensagem,
            status = compra.status,
            creationDate = compra.creationDate,
            compraProdutos = compraProdutos
        )
    }

    override fun findAll(): List<CompraResponse> {
        val compras = compraRepository.findAll()
        val comprasResponse = mutableListOf<CompraResponse>()
        for (compra in compras ){
            val compraProdutos = compra.compraProdutos.map { itensModel ->
                ItensResponse(
                    produtoModel = produtoService.findById(itensModel.produtoId),
                    quantidade = itensModel.quantidade
                )
            }
            comprasResponse.add(CompraResponse(
                id = compra.id!!,
                nomeComprador = compra.nomeComprador,
                numeroComprador = compra.numeroComprador,
                tipoEntrega = compra.tipoEntrega,
                valorTotal = compra.valorTotal,
                formaPagamento = compra.formaPagamento,
                mensagem = compra.mensagem,
                status = compra.status,
                creationDate = compra.creationDate,
                compraProdutos = compraProdutos
            ))
        }
        return comprasResponse

    }
    override fun findById(id: Long): CompraResponse {
        val compra = compraRepository.findById(id)
        val compraProdutos = compra.compraProdutos.map { itensModel ->
            ItensResponse(
                produtoModel = produtoService.findById(itensModel.produtoId),
                quantidade = itensModel.quantidade
            )
        }
        return CompraResponse(
            id = compra.id!!,
            nomeComprador = compra.nomeComprador,
            numeroComprador = compra.numeroComprador,
            tipoEntrega = compra.tipoEntrega,
            valorTotal = compra.valorTotal,
            formaPagamento = compra.formaPagamento,
            mensagem = compra.mensagem,
            status = compra.status,
            creationDate = compra.creationDate,
            compraProdutos = compraProdutos
        )
    }

    override fun mudarStatusCompra(id: Long, status: StatusCompra): CompraResponse {
        val compra = findById(id)
        if (compra.status != StatusCompra.PENDENTE) {
            throw IllegalStateException()
        }
        val itens = mutableListOf<ItensModel>()
        for (itemRequest in compra.compraProdutos) {
            val produtoModel = produtoService.findById(itemRequest.produtoModel.id!!)

            val item = ItensModel(
                produtoId = produtoModel.id!!,
                quantidade = itemRequest.quantidade,
                compraId = compra.id!!
            )
            itens.add(item)
        }
        val compraAtualizada = CompraModel(
            id = compra.id,
            nomeComprador = compra.nomeComprador,
            numeroComprador = compra.numeroComprador,
            tipoEntrega = compra.tipoEntrega,
            valorTotal = compra.valorTotal,
            formaPagamento = compra.formaPagamento,
            mensagem = compra.mensagem,
            status = status,
            creationDate = compra.creationDate,
            compraProdutos = itens
        )
        compraRepository.save(compraAtualizada)
        val compraResponse = compra.copy(
            status = status
        )
        return compraResponse
    }


    override fun listarComprasPendentes(status: StatusCompra): List<CompraResponse> {
        val comprasPendentes = compraRepository.findByStatus(status)
        return comprasPendentes.map { compra ->
            // Converter para CompraResponse se necessário
            CompraResponse(
                id = compra.id!!,
                nomeComprador = compra.nomeComprador,
                numeroComprador = compra.numeroComprador,
                tipoEntrega = compra.tipoEntrega,
                valorTotal = compra.valorTotal,
                formaPagamento = compra.formaPagamento,
                mensagem = compra.mensagem,
                status = compra.status,
                creationDate = compra.creationDate,
                compraProdutos = compra.compraProdutos.map { itensModel ->
                    ItensResponse(
                        produtoModel = produtoService.findById(itensModel.produtoId),
                        quantidade = itensModel.quantidade
                    )
                }
            )
        }
    }


}

