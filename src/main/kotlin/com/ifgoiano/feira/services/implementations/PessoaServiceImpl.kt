package com.ifgoiano.feira.services.interfaces

import com.ifgoiano.feira.api.request.NovaSenhaRequest
import com.ifgoiano.feira.api.request.PessoaRequest
import com.ifgoiano.feira.api.request.PessoaUpdateRequest
import com.ifgoiano.feira.api.response.PessoaResponse
import com.ifgoiano.feira.database.entities.Endereco
import com.ifgoiano.feira.database.entities.Login
import com.ifgoiano.feira.database.entities.Pessoa
import com.ifgoiano.feira.database.repositories.interfaces.EnderecoRepository
import com.ifgoiano.feira.database.repositories.interfaces.LoginRepository
import com.ifgoiano.feira.database.repositories.interfaces.PessoaRepository
import com.ifgoiano.feira.enum.Funcao
import com.ifgoiano.feira.enum.StatusLogin
import com.ifgoiano.feira.models.LoginModel
import com.ifgoiano.feira.models.PessoaModel
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class PessoaServiceImpl (
    val pessoaRepository: PessoaRepository,
    val loginRepository: LoginRepository,
    val enderecoRepository: EnderecoRepository
):PessoaService, UserDetailsService{
    override fun save(pessoaRequest: PessoaRequest): PessoaModel {
        if (loginRepository.existsByEmail(pessoaRequest.email)) NotFoundException()
        if (pessoaRepository.existsByCpf(pessoaRequest.cpf)) NotFoundException()
        val pessoaModel: PessoaModel = PessoaModel(
            nome = pessoaRequest.nome,
            cpf = pessoaRequest.cpf
                .replace("-", "")
                .replace(".",""),
            dataNascimento = pessoaRequest.dataNascimento,
            funcao = Funcao.ADMINISTRADOR,
            telefones = pessoaRequest.telefones
        )
        val pessoa = pessoaRepository.save(pessoaModel)
        val login = LoginModel(
            email = pessoaRequest.email,
            senha = BCryptPasswordEncoder().encode(pessoaRequest.senha),
            status = StatusLogin.ATIVO,
            funcionario = pessoa
        )
        val loginResposta:Login = Login.of(loginRepository.save(login))
        val enderecoModel = enderecoRepository.findByFuncionario(pessoa)
        val endereco = if (enderecoModel != null) {
            Endereco.of(enderecoModel, Pessoa.of(pessoa))
        } else {
            null
        }
        val pessoaResposta = pessoa.copy(login = loginResposta, endereco = endereco)
        return pessoaResposta
    }

    override fun findById(id: Long): PessoaModel {
        val pessoa = pessoaRepository.findById(id)
        return pessoa
    }

    override fun loadUserByUsername(username: String): UserDetails {
        return loginRepository.findByEmail(username)
    }

    override fun findAll(): List<PessoaModel> {
        return pessoaRepository.findAll()
    }

    override fun editar(email: String, pessoaUpdateRequest: PessoaUpdateRequest): PessoaModel {
        val login = loginRepository.findByEmail(email)
        val pessoa = pessoaRepository.findById(login.funcionario.id!!)
        val pessoaSave = pessoa.copy(
            nome = pessoaUpdateRequest.nome ?: pessoa.nome,
            dataNascimento = pessoaUpdateRequest.dataNascimento ?: pessoa.dataNascimento,
            telefones = pessoaUpdateRequest.telefones ?: pessoa.telefones,
            funcao = Funcao.ADMINISTRADOR
        )

        return pessoaRepository.save(pessoaSave)
    }

    override fun mudarSenha(email: String, novaSenha: NovaSenhaRequest): ResponseEntity<Any> {
        return try {
            val login = loginRepository.findByEmail(email)

            val senhaCriptografada = BCryptPasswordEncoder().encode(novaSenha.novaSenha)

            val loginAtualizado = login.copy(senha = senhaCriptografada)

            loginRepository.save(loginAtualizado)

            ResponseEntity.ok("Senha atualizada com sucesso")
        } catch (ex: NotFoundException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
        } catch(ex: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar a senha")
        }
    }

}