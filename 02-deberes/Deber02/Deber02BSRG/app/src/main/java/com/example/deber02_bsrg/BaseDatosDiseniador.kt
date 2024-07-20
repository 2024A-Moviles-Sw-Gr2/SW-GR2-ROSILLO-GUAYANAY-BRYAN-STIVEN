package com.example.deber02_bsrg

class BaseDatosDiseniador {

    companion object{
        var arregloDiseniador = arrayListOf<Diseniador>()

        fun agregarDiseniador(
            nombre:String,
            valorMercado:Long,
            numeroColecciones:Int,
            creadorUnisex:Boolean
        ){
            val diseniador = Diseniador(nombre,valorMercado,numeroColecciones,creadorUnisex)
            arregloDiseniador.add(diseniador)
        }

        fun actualizarDiseniador(
            posicion: Int,
            nombre: String,
            valorMercado: Long,
            colecciones: Int,
            esUnisex: Boolean
        ){
            arregloDiseniador[posicion].nombre = nombre
            arregloDiseniador[posicion].valorMercado = valorMercado
            arregloDiseniador[posicion].numeroColecciones = colecciones
            arregloDiseniador[posicion].creadorUnisex = esUnisex
        }

        fun eliminarDiseniador(posicion: Int){
            arregloDiseniador.removeAt(posicion)
        }

    }

}