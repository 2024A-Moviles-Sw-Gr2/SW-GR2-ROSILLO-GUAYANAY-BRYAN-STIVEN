package com.example.deber02_bsrg.baseDatos.ropa

import com.example.deber02_bsrg.entidades.Ropa
import java.time.LocalDate

class BaseDatosRopa {

    companion object{
        var arregloRopa = arrayListOf<Ropa>()

        fun agregarRopa(
            nombre:String,
            precio:Float,
            tendencia:Boolean,
            lanzamiento: String,
            aniosVidaUtil: Int
        ){
            val ropa = Ropa(nombre,precio,tendencia,lanzamiento,aniosVidaUtil)
            arregloRopa.add(ropa)
        }

        fun eliminarRopa(posicion: Int) {
            arregloRopa.removeAt(posicion)
        }

    }
}