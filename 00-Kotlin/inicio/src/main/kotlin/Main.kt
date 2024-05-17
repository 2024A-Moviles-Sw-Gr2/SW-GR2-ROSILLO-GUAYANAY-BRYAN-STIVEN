import java.util.*
import kotlin.collections.ArrayList

fun main() {
    print("Hola mundo")
    //Inmutable (No se puede RE ASIGNAR "=")
    val nombreVariableNoMut : String = "Bryan"

    //Mutable
    var nombreVariableMut : String = "Rosillo"
    nombreVariableMut = "Guayanay"

    //VAl es preferible a VAR


    //---------------------------------------------------------

    //Duck Tiping

    var ejemploVariable = "Cadena SUS"
    val ejemploVar2 = 290

    ejemploVariable.trim() //Al se String, tiene todos sus métodos

    //ejemploVariable = ejemploVar2 // ERROR

    val nombre:String = "Pedro"
    val sueldo:Double = 1.2
    val estadoCivil:Char = 'C'
    val mayorEdad:Boolean = true

    //Clases en java
    val fechaNacimiento: Date = Date()



    //-------------------------------------------------------------------------

    //When (Switch)

    val estadoCivilWhen = "C"

    when (estadoCivilWhen){
        ("C") -> {
            println("Casado")
        }
        ("S")->{
            println("Soltero")
        }

        else->{
            println("No sabemos")
        }

    }

    val esSoltero = (estadoCivilWhen=="S")
    val coqueteo = if(esSoltero) "Si" else "No" //if else chiquito

    //_------------------------------------------------------------------
    calcularSueldo(10.00)
    calcularSueldo(10.00, 15.00, 20.00)

    //Named Parameters
    //calcularSueldo(sueldo, tasa, bonoEspecial)
    calcularSueldo(10.00, bonoEspecial = 20.00)
    calcularSueldo(bonoEspecial = 20.00, sueldo = 10.00, tasa = 14.00)


    //-------------------------USO DE CLASES------------------------
    val sumaUno = Suma(1,1)
    val sumaDos = Suma(null,1)
    val sumaTres = Suma(1, null)
    val sumaCuatro = Suma(null,null)
    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()

    println(Suma.pi)
    println(Suma.historialSumas)


    //-------------------------------ARREGLOS-----------------------------
    //Arreglos estáticos
    val arregloEstatico: Array<Int> = arrayOf<Int>(1,2,3)
    println(arregloEstatico[0])

    //Arreglo dinámico
    val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(
        1,2,3,4,5,6,7,8,9,10
    )
    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)

    //------------------------OPERADORES PARA ARREGLOS----------------------------
    //FOR EACH -> Unit            Sirve para iterar un arreglo
    val respuestaForEach:Unit = arregloDinamico.forEach { valorActual: Int ->
        println("Valor actual: ${valorActual}")
    }

    //Utilizar el "it" que significa el elemento iterado
    arregloDinamico.forEach{ println("Valor actual (it): ${it}") }


    //MAP                 Modifica el arreglo
    //1) Enviamos el nuevo valor de la iteración
    //2) Devuelve un nuevo arreglo con valores de las iteraciones

    val respuestaMap: List<Double> = arregloDinamico.map{valorActual:Int ->
        return@map valorActual.toDouble() + 100.00
    }

    println(respuestaMap)

    val respuestaMapDos = arregloDinamico.map { it + 15 }


    //FILTER       Filtra el arreglo
    //1) Devolver una expresion (True o False)
    //2) Nuevo arreglo filtrado

    val respuestaFilter: List<Int> = arregloDinamico.filter {
        valorActual:Int ->
        val mayoresACinco: Boolean = valorActual > 5
        return@filter mayoresACinco
    }

    val respuestaFilterDos = arregloDinamico.filter { it <= 5 }
    println(respuestaFilter)
    println(respuestaFilterDos)


    // OR AND
    // OR -> ANY (Alguno cumple?)
    // AND -> ALL (Todos cumplen?)

    val respuestaAny: Boolean = arregloDinamico.any {
        valorActual: Int ->
        return@any(valorActual>5)
    }

    println(respuestaAny) //Retorna un TRUE

    val respuestaAll: Boolean = arregloDinamico.all {
        valorActual: Int ->
        return@all (valorActual>5)
    }

    println(respuestaAll) //Retorna un FALSE


    //REDUCE       Valor acumulado
    // Valor acumulado = 0 (Siempre empieza en 0 en Kotlin)

    val respuestaReduce: Int = arregloDinamico.reduce {
        acumulado: Int, valorActual:Int ->
        return@reduce (acumulado+valorActual)
    }

    println(respuestaReduce)


}


// void -> Unit
fun imprimirNombre(nombre:String):Unit{
    println("Nombre: ${nombre}")
}

fun calcularSueldo(
    sueldo: Double, //Requerido
    tasa: Double = 12.00, //Opcional (Defecto)
    bonoEspecial:Double?=null //Opcional (nullable)
    //Variable? -> "?" Es Nulleable (en algun momento puede ser nulo)
    //No solo funciona con double, sino con otro tipo de datos.
):Double{
    if(bonoEspecial==null){
        return sueldo*(100/tasa)
    }else{
        return sueldo*(100/tasa)*bonoEspecial
    }
}

//FORMA CONVENCIONAL
abstract class NumerosJava{
    protected val numeroUno:Int
    private val numeroDos:Int

    constructor(
        uno:Int,
        dos:Int
    ){
        this.numeroUno=uno
        this.numeroDos=dos
        println("Inicializando")
    }
}

//NUEVA FORMA

abstract class Numeros( //Constructor Primario
    //Caso 1) Parámetro normal
    // uno:Int , (parámetro (sin modificador de acceso))

    //Caso 2) Parámetro y propiedad (atributo)(private)
    // private var uno:Int (propiedad "instancia.uno")

    protected val numeroUno:Int,
    protected val numeroDos:Int,
    // parametroInutil:String  parámetro

){
    init { // Bloque de constructor primario opcional
        this.numeroUno
        this.numeroDos
        println("Inicializando")
    }
}

class Suma(unoParametro:Int, dosParametro:Int): Numeros(unoParametro, dosParametro){
    //Publico es opcional
    public val soyPublicoExplicito:String="Explicito"
    val soyPublicoImplicito:String="Implicito"

    constructor(
        uno: Int?,
        dos: Int
    ):this(
        if(uno==null) 0 else uno,
        dos
    )

    constructor(
        uno: Int,
        dos: Int?
    ):this(
        uno,
        if(dos==null) 0 else dos
    )

    constructor(
        uno: Int?,
        dos: Int?
    ):this(
        if(uno==null) 0 else uno,
        if(dos==null) 0 else dos
    )

    init {
        this.numeroUno
        this.numeroDos
        numeroUno // this. es opcional
        numeroDos

        this.soyPublicoExplicito
        soyPublicoImplicito
    }

    //---------------------------------------------------
    fun sumar():Int{
        val total = numeroUno + numeroDos
        // "Suma." o "NombreClase." es Opcional
        Suma.agregarHistorial(total)
        return total
    }

    companion object{ //Comparte entre todas las intancias, similar a Static
        //Funciones y variables

        val pi=3.14
        val historialSumas = arrayListOf<Int>()
        fun agregarHistorial(valorTotalSuma:Int){
            historialSumas.add(valorTotalSuma)
        }

    }




}

