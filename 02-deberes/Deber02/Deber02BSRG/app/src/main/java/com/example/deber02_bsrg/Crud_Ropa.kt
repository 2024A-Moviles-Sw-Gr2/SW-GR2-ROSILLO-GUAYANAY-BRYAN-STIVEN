package com.example.deber02_bsrg

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar

class Crud_Ropa : AppCompatActivity() {

    lateinit var adaptadorGlobal:ArrayAdapter<Ropa>

    val callbackContenidoIntentExplicito =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
                result->
            if(result.resultCode == Activity.RESULT_OK){
                adaptadorGlobal.notifyDataSetChanged()
                mostrarSnackBar("Ropa Creada")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud_ropa)

        val listView = findViewById<ListView>(R.id.lv_ropa)

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            BaseDatosRopa.arregloRopa
        )

        listView.adapter = adaptador
        adaptadorGlobal = adaptador
        adaptador.notifyDataSetChanged()

        val botonIrAgregarRopa = findViewById<Button>(R.id.btn_irAgregarRopa)
        botonIrAgregarRopa.setOnClickListener {
            val intentExplicito = Intent(this, AgregarRopa::class.java)
            callbackContenidoIntentExplicito.launch(intentExplicito)
        }
    }

    fun mostrarSnackBar(texto:String){
        val snack = Snackbar.make(
            findViewById(R.id.mainRopaLayout),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()

        Handler(Looper.getMainLooper()).postDelayed({
            snack.dismiss()
        }, 4000)
    }

}