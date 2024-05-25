class Diseniador {
    private var nombre:String
    private var valorMercado:Long
    private var numeroColecciones:Int
    private var creadorUnisex:Boolean
    private var ropa: ArrayList<Ropa>

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
}