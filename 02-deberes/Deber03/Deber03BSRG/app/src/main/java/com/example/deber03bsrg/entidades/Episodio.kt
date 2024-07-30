package com.example.deber03bsrg.entidades

class Episodio {

    val titulo: String
    val nombreImagen: String
    val descripcion: String

    constructor(titulo: String, nombreImagen: String, descripcion: String){
        this.titulo = titulo
        this.nombreImagen = nombreImagen
        this.descripcion = descripcion
    }

    override fun toString(): String {
        return "Episodio(titulo='$titulo', nombreImagen='$nombreImagen', descripcion='$descripcion')"
    }


}