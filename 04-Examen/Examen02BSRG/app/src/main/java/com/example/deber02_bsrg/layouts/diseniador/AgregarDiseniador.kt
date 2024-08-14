package com.example.deber02_bsrg.layouts.diseniador

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.deber02_bsrg.R
import com.example.deber02_bsrg.baseDatos.diseniador.TablaDiseniador

class AgregarDiseniador : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_diseniador)

        val tablaDiseniador = TablaDiseniador(this)

        val botonAgregarDiseniador = findViewById<Button>(R.id.btn_agregarDiseniador)
        botonAgregarDiseniador.setOnClickListener {
            val nombre = findViewById<EditText>(R.id.txt_nombreDiseniador).text.toString()
            val colecciones = findViewById<EditText>(R.id.txt_coleccionesDiseniador).text.toString().toInt()
            val esUnisex = findViewById<EditText>(R.id.txt_unisexDiseniador).text.toString()
            val ubicacion = findViewById<EditText>(R.id.txt_ubicacion).text.toString()

            tablaDiseniador.agregarDiseniador(
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