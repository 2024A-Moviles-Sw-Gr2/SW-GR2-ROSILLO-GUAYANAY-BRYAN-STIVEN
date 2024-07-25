package com.example.deber03bsrg

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdaptadorEpisodios(
    private val contexto: ListaEpisodios,
    private val lista: ArrayList<Episodio>,
    private val recyclerView: RecyclerView
):RecyclerView.Adapter<RecyclerViewAdaptadorEpisodios.MyViewHolder>(){

    inner class MyViewHolder(view: View):RecyclerView.ViewHolder(view){
        val titulo: TextView
        val imagenEpisodio: ImageView
        val descripcion: TextView

        init {
            titulo = view.findViewById<TextView>(R.id.lb_tituloEpisodio)
            imagenEpisodio = view.findViewById<ImageView>(R.id.img_episodio)
            descripcion = view.findViewById<TextView>(R.id.lb_descripcionEpisodio)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.recycler_view_vista_episodios, parent, false
        )
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return this.lista.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val episodioActual = lista[position]
        holder.titulo.text = episodioActual.titulo
        holder.descripcion.text = episodioActual.descripcion
        holder.imagenEpisodio.setImageResource(
            contexto.getResources().getIdentifier(episodioActual.nombreImagen, "drawable", contexto.packageName)
        )
    }
}