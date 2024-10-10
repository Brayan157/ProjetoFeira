package com.ifgoiano.feira.api.controller

import com.ifgoiano.feira.api.request.CompraRequest
import com.ifgoiano.feira.enum.StatusCompra
import com.ifgoiano.feira.services.interfaces.CompraService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/compra")
class CompraController(
    val compraService: CompraService
) {
    @PostMapping
    fun save(@RequestBody compraRequest: CompraRequest) = compraService.save(compraRequest)

    @GetMapping
    fun listar()=compraService.findAll()

    @GetMapping("/id/{id}")
    fun listarId(@PathVariable id: Long)=compraService.findById(id)

    @PutMapping("/mudarStatusAceito/{id}")
    fun aceitarCompra(@PathVariable id: Long)=compraService.mudarStatusCompra(id, StatusCompra.ACEITO)

    @PutMapping("/mudarStatusNegado/{id}")
    fun negarCompra(@PathVariable id: Long)=compraService.mudarStatusCompra(id, StatusCompra.NEGADO)

    @GetMapping("/pendentes")
    fun listarPendentes() = compraService.listarComprasPendentes(StatusCompra.PENDENTE)

    @GetMapping("/aceito")
    fun listarAceitos() = compraService.listarComprasPendentes(StatusCompra.ACEITO)

    @GetMapping("/negados")
    fun listarNegados() = compraService.listarComprasPendentes(StatusCompra.NEGADO)

}