package com.ifgoiano.feira.models
import java.time.LocalDateTime

data class TelefoneModel (
    val id:Long? = null,
    val numeroTelefone: String,
    val creationDate: LocalDateTime?,
    val updateData: LocalDateTime?
)