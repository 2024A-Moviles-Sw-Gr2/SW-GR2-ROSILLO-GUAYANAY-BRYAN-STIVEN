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

    ejemploVariable = ejemploVar2 // ERROR

    val nombre:String = "Pedro"
    val sueldo:Double = 1.2
    val estadoCivil:Char = 'C'
    val mayorEdad:Boolean = true

    //Clases en java
    val fechaNacimiento: Date = Date()


}