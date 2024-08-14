package com.example.deber02_bsrg.entidades

class Ropa(
    var id: Int,
    var nombre:String,
    var precio:Float,
    var tendencia:Boolean,
    var lanzamiento: String,
    var aniosVidaUtil: Int
){
    override fun toString(): String {
        val toStringRopa = "$nombre\nPrecio: $precio$ | ¿En tendencia?: $tendencia\nLanzamiento: ${lanzamiento.toString()} | Años vida útil: $aniosVidaUtil\n"
        return toStringRopa
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Ropa

        if (nombre != other.nombre) return false
        if (precio != other.precio) return false
        if (tendencia != other.tendencia) return false
        if (lanzamiento != other.lanzamiento) return false
        if (aniosVidaUtil != other.aniosVidaUtil) return false

        return true
    }

}