package com.example.deber02_bsrg.layouts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.deber02_bsrg.R
import com.example.deber02_bsrg.baseDatos.ropa.BaseDatosRopaSQLite
import com.example.deber02_bsrg.baseDatos.ropa.SQLiteRopaHelper
import com.example.deber02_bsrg.layouts.diseniador.Crud_Diseniador
import com.example.deber02_bsrg.layouts.ropa.Crud_Ropa

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        BaseDatosRopaSQLite.tablaRopa = SQLiteRopaHelper(this)


        //Para ir CRUD DISEÑADOR
        val botonInicio = findViewById<Button>(R.id.btn_iniciar)
        botonInicio.setOnClickListener {
            irActividad(Crud_Diseniador::class.java)
        }


        //Para ir a CRUD ROPA
        val botonIrCrudRopa = findViewById<Button>(R.id.btn_irCrudRopa)
        botonIrCrudRopa.setOnClickListener {
            irActividad(Crud_Ropa::class.java)
        }
    }

    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this,clase)
        startActivity(intent)
    }

}