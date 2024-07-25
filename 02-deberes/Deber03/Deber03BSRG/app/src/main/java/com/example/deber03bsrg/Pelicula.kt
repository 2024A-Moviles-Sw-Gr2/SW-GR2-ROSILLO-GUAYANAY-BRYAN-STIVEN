package com.example.deber03bsrg

class Pelicula{

    val nombre: String
    val nombreImagen: String

    constructor(nombre:String, nombreImagen: String){
        this.nombre = nombre
        this.nombreImagen = nombreImagen
    }

    override fun toString(): String {
        return "Pelicula(nombre='$nombre', nombreImagen='$nombreImagen')"
    }
}