package com.example.deber02_bsrg

import java.time.LocalDate

class BaseDatosRopa {

    companion object{
        var arregloRopa = arrayListOf<Ropa>()

        fun agregarRopa(
            nombre:String,
            precio:Float,
            tendencia:Boolean,
            lanzamiento: LocalDate,
            aniosVidaUtil: Int
        ){
            val ropa = Ropa(nombre,precio,tendencia,lanzamiento,aniosVidaUtil)
            arregloRopa.add(ropa)
        }

    }
}