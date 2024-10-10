package com.ifgoiano.feira.services.implementations

import com.ifgoiano.feira.api.request.EnderecoRequest
import com.ifgoiano.feira.api.request.EnderecoUpdateRequest
import com.ifgoiano.feira.database.entities.Pessoa
import com.ifgoiano.feira.database.repositories.interfaces.EnderecoRepository
import com.ifgoiano.feira.database.repositories.interfaces.LoginRepository
import com.ifgoiano.feira.models.EnderecoModel
import com.ifgoiano.feira.services.interfaces.EnderecoService
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class EnderecoServiceImpl(
    val enderecoRepository: EnderecoRepository,
    val loginRepository: LoginRepository
):EnderecoService {
    override fun salvar(endereco: EnderecoRequest, email:String): EnderecoModel {
        val login = loginRepository.findByEmail(email)
        val pessoa = login.funcionario
        val enderecoModel = EnderecoModel(
            id = null,
            cep = endereco.cep,
            bairro = endereco.bairro,
            cidade = endereco.cidade,
            complemento = endereco.complemento,
            creationDate = null,
            updateData = null,
            funcionario = pessoa

        )
        return enderecoRepository.save(enderecoModel)
    }

    override fun editar(endereco: EnderecoUpdateRequest, email: String): EnderecoModel {
        val login = loginRepository.findByEmail(email)
        val enderecoModel = enderecoRepository.findByFuncionario(login.funcionario)
        val enderecoSave = enderecoModel!!.copy(
            cep = endereco.cep ?: enderecoModel.cep,
            bairro = endereco.bairro ?: enderecoModel.bairro,
            cidade = endereco.cidade ?: enderecoModel.cidade,
            complemento = endereco.complemento ?: enderecoModel.complemento
        )
        return enderecoRepository.save(enderecoSave)
    }
}