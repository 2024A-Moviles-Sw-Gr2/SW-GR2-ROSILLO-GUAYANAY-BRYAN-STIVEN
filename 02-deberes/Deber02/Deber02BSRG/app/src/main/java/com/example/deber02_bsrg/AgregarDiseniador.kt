package com.example.deber02_bsrg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import com.google.android.material.snackbar.Snackbar

class AgregarDiseniador : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_diseniador)

        val listView = findViewById<ListView>(R.id.lv_ropaParaAgregarDiseniador)

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            BaseDatosRopa.arregloRopa
        )

        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val botonAgregarDiseniador = findViewById<Button>(R.id.btn_agregarDiseniador)
        botonAgregarDiseniador.setOnClickListener {
            val nombre = findViewById<EditText>(R.id.txt_nombreDiseniador).text.toString()
            val valorMercado = findViewById<EditText>(R.id.txt_valorMercadoDiseniador).text.toString().toLong()
            val colecciones = findViewById<EditText>(R.id.txt_coleccionesDiseniador).text.toString().toInt()
            val esUnisex = findViewById<EditText>(R.id.txt_unisexDiseniador).text.toString().toBoolean()
            val indicesRopa = findViewById<EditText>(R.id.txt_indicesRopaDiseniador).text.toString().split(",")

            val indices = indicesRopa.map {
                it.toInt()-1
            }

            BaseDatosDiseniador.agregarDiseniador(
                nombre,
                valorMercado,
                colecciones,
                esUnisex
            )

            indices.forEach {
                BaseDatosDiseniador.arregloDiseniador.last().ropa.add(BaseDatosRopa.arregloRopa[it])
            }


            devolverRespuesta()
        }
    }

    fun devolverRespuesta(){
        val intentDevolverRespuesta = Intent()
        setResult(RESULT_OK, intentDevolverRespuesta)
        finish()
    }

}