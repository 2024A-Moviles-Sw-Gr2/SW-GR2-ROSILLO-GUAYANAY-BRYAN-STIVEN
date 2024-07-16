package com.example.deber02_bsrg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar

class AgregarDiseniador : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_diseniador)

        val botonAgregarDiseniador = findViewById<Button>(R.id.btn_agregarDiseniador)
        botonAgregarDiseniador.setOnClickListener {
            val nombre = findViewById<EditText>(R.id.txt_nombreDiseniador).text.toString()
            val valorMercado = findViewById<EditText>(R.id.txt_valorMercadoDiseniador).text.toString().toLong()
            val colecciones = findViewById<EditText>(R.id.txt_coleccionesDiseniador).text.toString().toInt()
            val esUnisex = findViewById<EditText>(R.id.txt_unisexDiseniador).text.toString().toBoolean()
            val ropa = devolverArrayDeCadena(findViewById<EditText>(R.id.txt_ropaDiseniador).text.toString())
            BaseDatosDiseniador.agregarDiseniador(
                nombre,
                valorMercado,
                colecciones,
                esUnisex,
                ropa
            )
            mostrarSnackbar("Diseñador creado con Éxito")
        }
    }

    fun devolverArrayDeCadena(cadena: String): Array<String> {
        return cadena.split(",").map { it.trim() }.toTypedArray()
    }

    fun mostrarSnackbar(texto:String){
        val snack = Snackbar.make(
            findViewById(R.id.ly_agregarDiseniador),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }
}