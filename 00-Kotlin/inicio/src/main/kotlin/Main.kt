import java.util.*

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

abstract class Suma(unoParametro:Int, dosParametro:Int): Numeros(unoParametro, dosParametro){
    //Publico es opcional
    public val soyPublicoExplicito:String="Explicito"
    val soyPublicoImplicito:String="Implicito"

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

