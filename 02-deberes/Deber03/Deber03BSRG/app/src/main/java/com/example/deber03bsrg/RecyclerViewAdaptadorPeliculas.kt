package com.example.deber03bsrg

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdaptadorPeliculas(
    private val contexto: MainActivity,
    private val lista: ArrayList<Pelicula>,
    private val recyclerView: RecyclerView
):RecyclerView.Adapter<RecyclerViewAdaptadorPeliculas.MyViewHolder>(){

    inner class MyViewHolder(view: View):RecyclerView.ViewHolder(view){
        val nombrePelicula: TextView
        val imagenPelicula: ImageView

        init {
            nombrePelicula = view.findViewById<TextView>(R.id.lb_nombrePelicula)
            imagenPelicula = view.findViewById<ImageView>(R.id.img_pelicula)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.recycler_view_vista_peliculas, parent, false
        )
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return this.lista.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val peliculaActual = this.lista[position]
        holder.nombrePelicula.text = peliculaActual.nombre
        holder.imagenPelicula.setImageResource(
            contexto.getResources().getIdentifier(peliculaActual.nombreImagen, "drawable", contexto.packageName)
        )
    }

}