package com.ifgoiano.feira.api.controller

import com.ifgoiano.feira.api.request.ProdutoRequest
import com.ifgoiano.feira.api.request.ProdutoUpdateRequest
import com.ifgoiano.feira.services.interfaces.ProdutoService
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/produto")
class ProdutoController(
    val produtoService: ProdutoService
) {
    @PostMapping
    fun save(@RequestBody produtoRequest: ProdutoRequest) = produtoService.save(produtoRequest)

    @GetMapping("/listar/nome/{nomeProduto}")
    fun findByNomeProduto(
        @PathVariable nomeProduto:String,
        @PageableDefault(page = 0, size = 10) pageable: Pageable) = produtoService.findByNomeProduto(nomeProduto, pageable)

    @GetMapping("/listar")
    fun findByAll(@PageableDefault(page = 0, size = 10) pageable: Pageable) = produtoService.findAll(pageable)

    @GetMapping("/listar/id/{produtoId}")
    fun findById(@PathVariable produtoId: Long) = produtoService.findById(produtoId)

    @PutMapping("/update/id/{produtoId}")
    fun update(
        @RequestBody produtoRequest: ProdutoUpdateRequest,
        @PathVariable produtoId: Long
    ) = produtoService.update(produtoRequest, produtoId)

    @GetMapping("existe/nome/{nome}")
    fun existsByNomeProduto(@PathVariable nomeProduto: String) = produtoService.existsByNomeProduto(nomeProduto)
}