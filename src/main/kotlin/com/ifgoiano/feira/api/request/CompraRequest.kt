package com.ifgoiano.feira.api.request
import com.ifgoiano.feira.enum.FormaPagamento
import com.ifgoiano.feira.enum.TipoEntrega

data class CompraRequest(
    val nomeComprador:String,
    val numeroComprador:String,
    val tipoEntrega: TipoEntrega,
    val formaPagamento: FormaPagamento,
    val mensagem:String,
    val itens: List<ItensRequest>
)
