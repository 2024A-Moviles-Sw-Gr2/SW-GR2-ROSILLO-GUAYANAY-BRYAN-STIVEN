package com.example.deber03bsrg

class BaseDatosPeliculas {

    companion object{
        val listaPeliculas = ArrayList<Pelicula>()
        fun inicializarPeliculas(){
            this.listaPeliculas.add(Pelicula("Avengers", "avg"))
            this.listaPeliculas.add(Pelicula("Encanto", "enc"))
            this.listaPeliculas.add(Pelicula("Titanic", "tita"))
            this.listaPeliculas.add(Pelicula("Breaking Bad", "brb"))
            this.listaPeliculas.add(Pelicula("Cobra Kai", "cbk"))
        }
    }
}