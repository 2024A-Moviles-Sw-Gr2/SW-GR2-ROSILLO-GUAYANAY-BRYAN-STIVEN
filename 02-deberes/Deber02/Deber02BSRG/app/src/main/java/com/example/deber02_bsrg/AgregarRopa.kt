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
            val nombre = findViewById<EditText>(R.id.txt_nombreRopa).text.toString()
            val precio = findViewById<EditText>(R.id.txt_precioRopa).text.toString().toFloat()
            val tendencia = findViewById<EditText>(R.id.txt_tendenciaRopa).text.toString().toBoolean()
            val arregloFechaLanzamiento = findViewById<EditText>(R.id.txt_fechaLanzamientoRopa).text.toString().split("/")
            val fechaLanzamiento = LocalDate.of(arregloFechaLanzamiento[2].toInt(),arregloFechaLanzamiento[1].toInt(),arregloFechaLanzamiento[2].toInt())
            val vidaUtil = findViewById<EditText>(R.id.txt_vidaUtilRopa).text.toString().toInt()

            BaseDatosRopa.agregarRopa(
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