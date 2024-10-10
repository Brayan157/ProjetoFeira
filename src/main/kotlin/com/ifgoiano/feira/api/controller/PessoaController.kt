package com.ifgoiano.feira.api.controller

import com.ifgoiano.feira.api.request.NovaSenhaRequest
import com.ifgoiano.feira.api.request.PessoaRequest
import com.ifgoiano.feira.api.request.PessoaUpdateRequest
import com.ifgoiano.feira.api.response.PessoaResponse
import com.ifgoiano.feira.models.PessoaModel
import com.ifgoiano.feira.services.interfaces.PessoaService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
@RestController
@RequestMapping("/pessoa")
data class PessoaController(
    val pessoaService: PessoaService
){
    @PostMapping
    fun save(@RequestBody pessoaRequest: PessoaRequest) = pessoaService.save(pessoaRequest)

    @GetMapping("/{id}")
    fun listarPorId(@PathVariable id: Long) = pessoaService.findById(id)

    @GetMapping("/listar")
    fun listarTodos():List<PessoaModel> = pessoaService.findAll()

    @PutMapping("/editar/{id}")
    fun editar(@PathVariable id: Long,
               @RequestBody pessoaUpdateRequest: PessoaUpdateRequest): PessoaModel = pessoaService.editar(id, pessoaUpdateRequest)


}

