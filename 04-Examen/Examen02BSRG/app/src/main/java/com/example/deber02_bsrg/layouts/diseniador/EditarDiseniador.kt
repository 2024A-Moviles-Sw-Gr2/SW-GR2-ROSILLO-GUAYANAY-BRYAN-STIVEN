package com.example.deber02_bsrg.layouts.diseniador

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.deber02_bsrg.R
import com.example.deber02_bsrg.baseDatos.BaseDatosDiseniador

class EditarDiseniador : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_diseniador)

        val posicionItemDiseniador = intent.getIntExtra("posicionDiseniador",0)
        val objetoDiseniadorActualizar = BaseDatosDiseniador.arregloDiseniador[posicionItemDiseniador]

        findViewById<EditText>(R.id.txt_nombreDiseniadorActualizar).setText(objetoDiseniadorActualizar.nombre.toString())
        findViewById<EditText>(R.id.txt_valorMercadoDiseniadorActualizar).setText(objetoDiseniadorActualizar.valorMercado.toString())
        findViewById<EditText>(R.id.txt_coleccionesDiseniadorActualizar).setText(objetoDiseniadorActualizar.numeroColecciones.toString())
        findViewById<EditText>(R.id.txt_unisexDiseniadorActualizar).setText(objetoDiseniadorActualizar.creadorUnisex.toString())


        val botonActualizarDiseniador = findViewById<Button>(R.id.btn_actualizarDiseniador)
        botonActualizarDiseniador.setOnClickListener {
            val nombre = findViewById<EditText>(R.id.txt_nombreDiseniadorActualizar).text.toString()
            val valorMercado = findViewById<EditText>(R.id.txt_valorMercadoDiseniadorActualizar).text.toString().toLong()
            val colecciones = findViewById<EditText>(R.id.txt_coleccionesDiseniadorActualizar).text.toString().toInt()
            val esUnisex = findViewById<EditText>(R.id.txt_unisexDiseniadorActualizar).text.toString().toBoolean()

            BaseDatosDiseniador.actualizarDiseniador(
                posicionItemDiseniador,
                nombre,
                valorMercado,
                colecciones,
                esUnisex
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