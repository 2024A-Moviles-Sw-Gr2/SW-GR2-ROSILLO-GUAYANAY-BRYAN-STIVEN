
import java.io.File
import java.util.*

class Ropa {
    var nombre:String
    var precio:Float
    var tendencia:Boolean
    var lanzamiento: Date
    var aniosVidaUtil:Int

    constructor(
        nombre: String,
        precio: Float,
        tendencia: Boolean,
        lanzamiento: Date,
        aniosVidaUtil: Int
    ) {
        this.nombre = nombre
        this.precio = precio
        this.tendencia = tendencia
        this.lanzamiento = lanzamiento
        this.aniosVidaUtil = aniosVidaUtil
    }



    companion object{
        fun agregarRegistroRopa(ropa:Ropa){
            var cadena = "\n"+ropa.nombre + "," + ropa.precio + "," + ropa.tendencia + "," + ropa.lanzamiento + "," + ropa.aniosVidaUtil
            File("src/main/resources/ropa.txt").appendText(cadena+";")
        }

        fun obtenerRegistroRopa():Array<Array<String>>{

            var stringTokenizerFila = StringTokenizer(File("src/main/resources/ropa.txt").readText(), ";")
            var matriz: Array<Array<String>> = Array(stringTokenizerFila.countTokens()){Array(5) { "" } }
            var fila:Int = 0
            var columna:Int = 0

            while(stringTokenizerFila.hasMoreTokens()){
                var valor = stringTokenizerFila.nextToken().toString()
                if(!valor.equals("\n")){
                    var stringTokenizerCampo = StringTokenizer(valor,",")
                    while (stringTokenizerCampo.hasMoreTokens()){
                        matriz[fila][columna] = stringTokenizerCampo.nextToken().toString()
                        columna++
                    }
                    columna = 0
                    fila++
                }
            }

            return matriz

        }

        fun eliminarRegistroRopa(id:Int){
            var stringTokenizerFila = StringTokenizer(File("src/main/resources/ropa.txt").readText(), ";")
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
            File("src/main/resources/ropa.txt").writeText(nuevoGrupoRegistros)

        }

        fun actualizarRegistroRopa(nuevosRegistros:String){
            File("src/main/resources/ropa.txt").writeText(nuevosRegistros)
        }
    }




}