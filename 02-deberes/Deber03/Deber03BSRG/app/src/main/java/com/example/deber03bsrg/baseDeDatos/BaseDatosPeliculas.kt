package com.example.deber03bsrg.baseDeDatos

import com.example.deber03bsrg.entidades.Pelicula

class BaseDatosPeliculas {
    companion object{
        val listaPeliculas = ArrayList<Pelicula>()
        fun inicializarPeliculas(){
            listaPeliculas.add(Pelicula("Avengers", "avg"))
            listaPeliculas.add(Pelicula("Encanto", "enc"))
            listaPeliculas.add(Pelicula("Titanic", "tita"))
            listaPeliculas.add(Pelicula("Breaking Bad", "brb"))
            listaPeliculas.add(Pelicula("Cobra Kai", "cbk"))
        }
    }
}