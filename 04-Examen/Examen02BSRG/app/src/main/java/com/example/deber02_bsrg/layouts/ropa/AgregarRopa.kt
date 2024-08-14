package com.example.deber02_bsrg.layouts.ropa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.deber02_bsrg.R
import com.example.deber02_bsrg.baseDatos.ropa.TablaRopa

class AgregarRopa : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_ropa)

        val tablaRopa = TablaRopa(this)
        val id_diseniador = intent.getIntExtra("id_diseniador", -1)

        val botonCrearRopa = findViewById<Button>(R.id.btn_crearRopa)
        botonCrearRopa.setOnClickListener {
            //Recuperamos los datos
            val nombre = findViewById<EditText>(R.id.txt_nombreRopa).text.toString()
            val precio = findViewById<EditText>(R.id.txt_precioRopa).text.toString().toFloat()
            val tendencia = findViewById<EditText>(R.id.txt_tendenciaRopa).text.toString().toBoolean()
            val fechaLanzamiento = findViewById<EditText>(R.id.txt_fechaLanzamientoRopa).text.toString()
            val vidaUtil = findViewById<EditText>(R.id.txt_vidaUtilRopa).text.toString().toInt()

            //Agregamos a la lista global de ropa
            tablaRopa.agregarRopa(
                nombre,
                precio,
                tendencia,
                fechaLanzamiento,
                vidaUtil,
                id_diseniador
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