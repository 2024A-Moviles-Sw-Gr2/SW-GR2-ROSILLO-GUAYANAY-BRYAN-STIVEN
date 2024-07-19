package com.example.deber02_bsrg

class BaseDatosDiseniador {

    companion object{
        var arregloDiseniador = arrayListOf<Diseniador>()

        fun agregarDiseniador(
            nombre:String,
            valorMercado:Long,
            numeroColecciones:Int,
            creadorUnisex:Boolean,
            ropa: Array<Int>
        ){
            val diseniador = Diseniador(nombre,valorMercado,numeroColecciones,creadorUnisex,ropa)
            arregloDiseniador.add(diseniador)
        }


        fun buscarDiseniador(nombre:String):Diseniador?{
            return arregloDiseniador.find { diseniador -> diseniador.nombre.equals(nombre) }
        }

        fun eliminarDiseniador(posicion: Int){
            arregloDiseniador.removeAt(posicion)
        }

        /*
        fun actualizarDiseniador(
            nombre:String,
            valorMercado:Long,
            numeroColecciones:Int,
            creadorUnisex:Boolean,
            ropa: Array<String>
        ){
            if(this.buscarDiseniador(nombre)!= null){
                val nuevoDiseniador = Diseniador(nombre,valorMercado,numeroColecciones,creadorUnisex,ropa)
                arregloDiseniador = arregloDiseniador.map {
                    if(it.nombre.equals(nombre)) nuevoDiseniador else it
                }.toMutableList() as ArrayList<Diseniador>
            }
        }

         */

    }

}