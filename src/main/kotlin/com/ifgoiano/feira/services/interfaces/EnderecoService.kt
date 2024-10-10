package com.ifgoiano.feira.services.interfaces

import com.ifgoiano.feira.api.request.EnderecoRequest
import com.ifgoiano.feira.api.request.EnderecoUpdateRequest
import com.ifgoiano.feira.models.EnderecoModel

interface EnderecoService {
    fun salvar(endereco: EnderecoRequest, email:String): EnderecoModel
    fun editar(endereco: EnderecoUpdateRequest, email: String): EnderecoModel
}