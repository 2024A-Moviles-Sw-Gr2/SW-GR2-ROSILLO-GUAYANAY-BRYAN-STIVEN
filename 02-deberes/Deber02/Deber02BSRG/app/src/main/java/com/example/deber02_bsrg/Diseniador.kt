package com.example.deber02_bsrg

class Diseniador(
    var nombre: String,
    var valorMercado:Long,
    var numeroColecciones:Int,
    var creadorUnisex:Boolean,
    var ropa: Array<Int>
){

    override fun toString(): String {
        val toStringDiseniador = "$nombre\nValor de mercado: $valorMercado $\nColecciones: $numeroColecciones | Unisex: $creadorUnisex"
        return toStringDiseniador
    }

}