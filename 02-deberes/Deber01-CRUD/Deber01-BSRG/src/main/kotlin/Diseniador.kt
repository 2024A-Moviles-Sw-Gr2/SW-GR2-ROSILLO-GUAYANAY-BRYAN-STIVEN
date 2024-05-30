import java.io.File
import java.util.*
import kotlin.collections.ArrayList

class Diseniador {
    var nombre:String
    var valorMercado:Long
    var numeroColecciones:Int
    var creadorUnisex:Boolean
    var ropa: ArrayList<Ropa>

    constructor(
        nombre: String,
        valorMercado: Long,
        numeroColecciones: Int,
        creadorUnisex: Boolean,
    ) {
        this.nombre = nombre
        this.valorMercado = valorMercado
        this.numeroColecciones = numeroColecciones
        this.creadorUnisex = creadorUnisex
        this.ropa = ArrayList<Ropa>()
    }

    companion object{

        fun obtenerRegistroDiseniador(): Array<Array<String>>{
            var stringTokenizerFila = StringTokenizer(File("src/main/resources/diseniador.txt").readText(), ";")
            var matriz: Array<Array<String>> = Array(stringTokenizerFila.countTokens()){Array(5) { "" } }
            var fila:Int = 0
            var columna:Int = 0

            while(stringTokenizerFila.hasMoreTokens()){
                var valor = stringTokenizerFila.nextToken().toString()
                if(!valor.equals("\n")){
                    var stringTokenizerCampo = StringTokenizer(valor,",")
                    while (stringTokenizerCampo.hasMoreTokens()){
                        if(columna == 4){

                            var indicesRopa = stringTokenizerCampo.nextToken()
                            for(i in 0..indicesRopa.length-1){
                                if(i!= indicesRopa.length-1){
                                    matriz[fila][columna] += Ropa.obtenerRegistroRopa()[indicesRopa[i].toString().toInt()][0].toString() + " "
                                }else{
                                    matriz[fila][columna] += Ropa.obtenerRegistroRopa()[indicesRopa[i].toString().toInt()][0].toString()
                                }

                            }
                        }else{
                            matriz[fila][columna] = stringTokenizerCampo.nextToken().toString()
                        }
                        columna++
                    }
                    columna = 0
                    fila++
                }
            }

            return matriz
        }

        fun agregarRegistroDiseniador(diseniador: Diseniador) {
            var indicesRopa = ""
            var registrosRopa = Ropa.obtenerRegistroRopa()

            for(i in 0..diseniador.ropa.size-1){
                for(j in 0..registrosRopa.size-1){
                    if(registrosRopa[j][0].replace("\n","").equals(diseniador.ropa[i].nombre)){
                        indicesRopa += j.toString()
                    }
                }
            }

            var cadena ="\n"+diseniador.nombre + "," + diseniador.valorMercado + "," + diseniador.numeroColecciones + "," + diseniador.creadorUnisex + "," + indicesRopa + ";"
            File("src/main/resources/diseniador.txt").appendText(cadena)
        }

        fun eliminarRegistroDiseniador(id: Int) {
            var stringTokenizerFila = StringTokenizer(File("src/main/resources/diseniador.txt").readText(), ";")
            var nuevoGrupoRegistros = ""

            var fila = 0
            while(stringTokenizerFila.hasMoreTokens()){
                var registro = ""
                if(fila != id){
                    registro = stringTokenizerFila.nextToken().toString()
                    if(!registro.equals("\n")){
                        nuevoGrupoRegistros += registro + ";"
                    }else{
                        break
                    }
                }else{
                    stringTokenizerFila.nextToken()
                }
                fila++
            }
            File("src/main/resources/Diseniador.txt").writeText(nuevoGrupoRegistros)

        }

        fun actualizarRegistroDiseniador(nuevosRegistros: String) {
            File("src/main/resources/diseniador.txt").writeText(nuevosRegistros)
        }


    }



}