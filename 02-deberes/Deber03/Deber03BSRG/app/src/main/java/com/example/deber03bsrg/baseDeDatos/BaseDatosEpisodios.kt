package com.example.deber03bsrg.baseDeDatos

import com.example.deber03bsrg.entidades.Episodio

class BaseDatosEpisodios {
    companion object{
        val listaEpisodios = ArrayList<Episodio>()
        fun inicializarEpisodios(){
            listaEpisodios.add(Episodio("Episodio 1", "epuno", "El protagonista sale con sus amigos de fiesta."))
            listaEpisodios.add(Episodio("Episodio 2", "epdos", "Todos han muerto en el accidente."))
            listaEpisodios.add(Episodio("Episodio 3", "eptres", "Es hora de viajar al pasado para salvar el futuro."))
            listaEpisodios.add(Episodio("Episodio 4", "epcuatro", "Al parecer, no sirvió de nada. Hay que volver al presente."))
            listaEpisodios.add(Episodio("Episodio 5", "epcinco", "¿Por qué ahora Bolivia es potencia mundial?."))
            listaEpisodios.add(Episodio("Episodio 6", "epseis", "Hay que volver al pasado otra vez para arreglar esto."))
            listaEpisodios.add(Episodio("Episodio 7", "epsiete", "Hemos regresado y ahora no existe Betty la fea :("))
        }
    }
}