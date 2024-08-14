package com.example.deber02_bsrg.entidades

import com.example.deber02_bsrg.entidades.Ropa

class Diseniador(
    var id: Int,
    var nombre: String,
    var numeroColecciones:Int,
    var creadorUnisex:String,
    var ubicacion: String
){

    override fun toString(): String {
        val toStringDiseniador = "$nombre\nColecciones: $numeroColecciones | Unisex: $creadorUnisex\n"
        return toStringDiseniador
    }

}