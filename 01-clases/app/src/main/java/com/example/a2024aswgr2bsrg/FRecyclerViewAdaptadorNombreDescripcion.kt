package com.example.a2024aswgr2bsrg

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FRecyclerViewAdaptadorNombreDescripcion(
    private val contexto: FReyclerView,
    private val lista: ArrayList<BEntrenador>,
    private val recyclerView: RecyclerView
):RecyclerView.Adapter<
        FRecyclerViewAdaptadorNombreDescripcion.MyViewHolder
        >()  {

    inner class MyViewHolder(view: View):RecyclerView.ViewHolder(view){
        val nombreTextView: TextView
        val descripcionTextView: TextView
        val likesTestView: TextView
        val accionBoton: Button
        var numeroLikes = 0

        init {
            nombreTextView = view.findViewById(R.id.tv_nombre)
            descripcionTextView = view.findViewById(R.id.tv_descripcion)
            likesTestView = view.findViewById(R.id.tv_likes)
            accionBoton = view.findViewById(R.id.btn_dar_like)
            accionBoton.setOnClickListener { anadirLike() }
        }

        fun anadirLike(){
            numeroLikes++
            likesTestView.text = numeroLikes.toString()
            contexto.aumentarTotalLikes()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val itemView = LayoutInflater.from(parent.context).inflate(
           R.layout.recycler_view_vista, parent, false
       )
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return this.lista.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val entrenadorActual = this.lista[position]
        holder.nombreTextView.text = entrenadorActual.nombre
        holder.descripcionTextView.text = entrenadorActual.descripcion
        holder.likesTestView.text = holder.numeroLikes.toString()
        holder.accionBoton.text = "ID: ${entrenadorActual.id} " + "Nombre: ${entrenadorActual.nombre}"
    }
}