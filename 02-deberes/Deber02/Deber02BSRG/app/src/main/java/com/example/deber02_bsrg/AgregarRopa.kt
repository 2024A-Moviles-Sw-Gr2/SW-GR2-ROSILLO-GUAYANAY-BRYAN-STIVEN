package com.example.deber02_bsrg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import java.time.LocalDate
import kotlin.reflect.typeOf

class AgregarRopa : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_ropa)

        val botonCrearRopa = findViewById<Button>(R.id.btn_crearRopa)
        botonCrearRopa.setOnClickListener {
            //Recuperamos los datos
            val nombre = findViewById<EditText>(R.id.txt_nombreRopa).text.toString()
            val precio = findViewById<EditText>(R.id.txt_precioRopa).text.toString().toFloat()
            val tendencia = findViewById<EditText>(R.id.txt_tendenciaRopa).text.toString().toBoolean()
            val arregloFechaLanzamiento = findViewById<EditText>(R.id.txt_fechaLanzamientoRopa).text.toString().split("/")
            val fechaLanzamiento = LocalDate.of(arregloFechaLanzamiento[2].toInt(),arregloFechaLanzamiento[1].toInt(),arregloFechaLanzamiento[0].toInt())
            val vidaUtil = findViewById<EditText>(R.id.txt_vidaUtilRopa).text.toString().toInt()

            //Agregamos a la lista global de ropa
            BaseDatosRopa.agregarRopa(
                nombre,
                precio,
                tendencia,
                fechaLanzamiento,
                vidaUtil
            )

            //Agregamos la última ropa agregada al diseñador en caso de que exista.
            val indiceDiseniador = intent.getIntExtra("posicionDiseniador", -1)
            if(indiceDiseniador!=-1){
                BaseDatosDiseniador.arregloDiseniador[indiceDiseniador].ropa.add(BaseDatosRopa.arregloRopa.last())
            }

            //Devolvemos una respuesta exitosa.
            devolverRespuesta()

        }
    }

    fun devolverRespuesta(){
        val intentDevolverRespuesta = Intent()
        setResult(RESULT_OK, intentDevolverRespuesta)
        finish()
    }
}