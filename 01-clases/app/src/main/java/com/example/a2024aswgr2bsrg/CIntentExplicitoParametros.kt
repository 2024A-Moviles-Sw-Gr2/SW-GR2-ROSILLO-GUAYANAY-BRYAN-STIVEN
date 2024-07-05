package com.example.a2024aswgr2bsrg

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CIntentExplicitoParametros : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cintent_explicito_parametros)

        val nombre = intent.getStringExtra("nombre")
        val apellido = intent.getStringExtra("apellido")
        val edad = intent.getIntExtra("edad", 0)
        val entrenador = intent.getParcelableExtra<BEntrenador>("entrenador")
        val boton = findViewById<Button>(R.id.btn_devolver_respuesta)
        boton.setOnClickListener{devolverRespuesta()}


    }

    private fun devolverRespuesta() {
        val intentDevolverRespuesta = Intent()
        intentDevolverRespuesta.putExtra("nombreModificado","Padrato")
        setResult(RESULT_OK, intentDevolverRespuesta)
        finish()
    }
}