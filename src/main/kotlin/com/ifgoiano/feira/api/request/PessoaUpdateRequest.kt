package com.ifgoiano.feira.api.request

import java.time.LocalDate

data class PessoaUpdateRequest(
    val nome:String? = null,
    val dataNascimento: LocalDate? = null,
    val telefones:String? = null,
)
