package com.example.deber02_bsrg.layouts.diseniador

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.deber02_bsrg.R
import com.example.deber02_bsrg.baseDatos.diseniador.TablaDiseniador

class EditarDiseniador : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_diseniador)
        val tablaDiseniador = TablaDiseniador(this)

        val posicionItemDiseniador = intent.getIntExtra("posicionDiseniador",0)
        val objetoDiseniadorActualizar = tablaDiseniador.listarDiseniador()[posicionItemDiseniador]

        findViewById<EditText>(R.id.txt_nombreDiseniadorActualizar).setText(objetoDiseniadorActualizar.nombre.toString())
        findViewById<EditText>(R.id.txt_coleccionesDiseniadorActualizar).setText(objetoDiseniadorActualizar.numeroColecciones.toString())
        findViewById<EditText>(R.id.txt_unisexDiseniadorActualizar).setText(objetoDiseniadorActualizar.creadorUnisex.toString())
        findViewById<EditText>(R.id.txt_ubicacionDiseniadorActualizar).setText(objetoDiseniadorActualizar.ubicacion.toString())


        val botonActualizarDiseniador = findViewById<Button>(R.id.btn_actualizarDiseniador)
        botonActualizarDiseniador.setOnClickListener {
            val nombre = findViewById<EditText>(R.id.txt_nombreDiseniadorActualizar).text.toString()
            val colecciones = findViewById<EditText>(R.id.txt_coleccionesDiseniadorActualizar).text.toString().toInt()
            val esUnisex = findViewById<EditText>(R.id.txt_unisexDiseniadorActualizar).text.toString()
            val ubicacion = findViewById<EditText>(R.id.txt_ubicacionDiseniadorActualizar).text.toString()

            tablaDiseniador.actualizarDiseniador(
                objetoDiseniadorActualizar.id,
                nombre,
                colecciones,
                esUnisex,
                ubicacion
            )
            devolverRespuesta()
        }

    }

    fun devolverRespuesta(){
        val intentDevolverRespuesta = Intent()
        setResult(RESULT_OK, intentDevolverRespuesta)
        finish()
    }

}