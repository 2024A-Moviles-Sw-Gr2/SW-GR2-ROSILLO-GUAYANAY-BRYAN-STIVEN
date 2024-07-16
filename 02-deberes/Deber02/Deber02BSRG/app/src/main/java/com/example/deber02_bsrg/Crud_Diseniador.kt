package com.example.deber02_bsrg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class Crud_Diseniador : AppCompatActivity() {

    val registros = BaseDatosDiseniador.arregloDiseniador


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud_diseniador)

        val listView = findViewById<ListView>(R.id.lv_diseniador)

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            registros
        )

        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()



        val botonIrAgregar = findViewById<Button>(R.id.btn_irAgregar)
        botonIrAgregar.setOnClickListener {
            irActividad(AgregarDiseniador::class.java)
        }
    }

    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this,clase)
        startActivity(intent)
    }
}