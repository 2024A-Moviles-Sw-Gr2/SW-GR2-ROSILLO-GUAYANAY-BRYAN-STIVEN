package com.example.deber02_bsrg.layouts.ropa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.deber02_bsrg.R
import com.example.deber02_bsrg.baseDatos.ropa.TablaRopa
import com.example.deber02_bsrg.entidades.Ropa

class EditarRopa : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_ropa)
        val tablaRopa = TablaRopa(this)

        //Obtenemos el objeto ropa de la lista del diseñador, o de la lista global.
        val posicionItemRopa = intent.getIntExtra("posicionRopa",-1)
        val id_diseniador = intent.getIntExtra("id_diseniador",-1)
        val objetoRopaEditar: Ropa = tablaRopa.listarRopa(id_diseniador)[posicionItemRopa]


        //Del objeto rescatamos sus datos, y los enviamos a la actividad de actualizar.
        findViewById<EditText>(R.id.txt_nombreRopaActualizar).setText(objetoRopaEditar.nombre)
        findViewById<EditText>(R.id.txt_precioRopaActualizar).setText(objetoRopaEditar.precio.toString())
        findViewById<EditText>(R.id.txt_tendenciaRopaActualizar).setText(objetoRopaEditar.tendencia.toString())
        findViewById<EditText>(R.id.txt_lanzamientoRopaActualizar).setText(objetoRopaEditar.lanzamiento)
        findViewById<EditText>(R.id.txt_vidaUtilRopaActualizar).setText(objetoRopaEditar.aniosVidaUtil.toString())

        //Nuevamente, rescatamos los datos de la interfaz, y creamos un nuevo objeto.
        val botonActualizarRopa = findViewById<Button>(R.id.btn_actualizarRopa)
        botonActualizarRopa.setOnClickListener {
            val nombre = findViewById<EditText>(R.id.txt_nombreRopaActualizar).text.toString()
            val precio = findViewById<EditText>(R.id.txt_precioRopaActualizar).text.toString().toFloat()
            val tendencia = findViewById<EditText>(R.id.txt_tendenciaRopaActualizar).text.toString().toBoolean()
            val fechaLanzamiento = findViewById<EditText>(R.id.txt_lanzamientoRopaActualizar).text.toString()
            val vidaUtil = findViewById<EditText>(R.id.txt_vidaUtilRopaActualizar).text.toString().toInt()

            tablaRopa.actualizarRopa(
                objetoRopaEditar.id,
                nombre,
                precio,
                tendencia,
                fechaLanzamiento,
                vidaUtil
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