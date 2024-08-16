package com.example.proyectoiib.vista.asignatura

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoiib.R
import com.example.proyectoiib.modelo.entidades.asignatura.TablaAsignatura
import com.example.proyectoiib.vista.recyclerView.RecyclerViewAsignaturas
import com.google.android.material.snackbar.Snackbar

class AsignaturasLayout : AppCompatActivity() {
    val tablaAsignatura = TablaAsignatura(this)

    val callbackContenidoIntentExplicito =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
                result->
            if(result.resultCode == Activity.RESULT_OK){
                if(result.data != null){
                    inicializarRecyclerView()
                    mostrarSnackBar("Operación exitosa")
                }
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asignaturas)
        inicializarRecyclerView()

        val botonAgregarAsignatura = findViewById<Button>(R.id.btn_agregarAsignatura)
        botonAgregarAsignatura.setOnClickListener {
            val intent = Intent(this, NuevaAsignatura::class.java)
            callbackContenidoIntentExplicito.launch(intent)
        }


    }

    fun inicializarRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.rv_asignaturas)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

        val adaptador = RecyclerViewAsignaturas(
            this,
            tablaAsignatura.listarAsignatura(),
            recyclerView
        )

        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        adaptador.notifyDataSetChanged()

    }


    fun mostrarSnackBar(texto:String){
        val snack = Snackbar.make(
            findViewById(R.id.ly_inicioAsignaturas),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()

        Handler(Looper.getMainLooper()).postDelayed({
            snack.dismiss()
        }, 4000)
    }


}