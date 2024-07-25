package com.example.deber03bsrg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inicializarRecyclerView()
    }

    fun inicializarRecyclerView() {
        BaseDatosPeliculas.inicializarPeliculas()
        val recyclerView = findViewById<RecyclerView>(R.id.rv_peliculas)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager

        val adaptador = RecyclerViewAdaptadorPeliculas(
            this,
            BaseDatosPeliculas.listaPeliculas,
            recyclerView
        )

        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        adaptador.notifyDataSetChanged()

        val botonSerie = findViewById<Button>(R.id.btn_irSerie)
        botonSerie.setOnClickListener {
            val intent = Intent(this, ListaEpisodios::class.java)
            startActivity(intent)
        }

    }
}