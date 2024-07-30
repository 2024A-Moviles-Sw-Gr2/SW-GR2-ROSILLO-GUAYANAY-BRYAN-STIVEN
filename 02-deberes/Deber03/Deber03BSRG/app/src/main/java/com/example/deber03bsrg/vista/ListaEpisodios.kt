package com.example.deber03bsrg.vista

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.deber03bsrg.R
import com.example.deber03bsrg.vista.recycler.RecyclerViewAdaptadorEpisodios
import com.example.deber03bsrg.baseDeDatos.BaseDatosEpisodios

class ListaEpisodios : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_episodios)
        inicializarRecyclerView()
    }

    fun inicializarRecyclerView(){
        BaseDatosEpisodios.inicializarEpisodios()

        val recyclerView = findViewById<RecyclerView>(R.id.rv_listaEpisodios)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

        val adaptador = RecyclerViewAdaptadorEpisodios(
            this,
            BaseDatosEpisodios.listaEpisodios,
            recyclerView
        )

        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        adaptador.notifyDataSetChanged()
    }
}