package com.example.deber02_bsrg.layouts.ropa

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import com.example.deber02_bsrg.R
import com.example.deber02_bsrg.baseDatos.BaseDatosDiseniador
import com.example.deber02_bsrg.baseDatos.ropa.BaseDatosRopa
import com.example.deber02_bsrg.baseDatos.ropa.BaseDatosRopaSQLite
import com.example.deber02_bsrg.entidades.Ropa
import com.google.android.material.snackbar.Snackbar

class Crud_Ropa : AppCompatActivity() {

    //Adaptador que sirve para ir refrescando la lista de ropa.
    lateinit var adaptadorGlobal:ArrayAdapter<Ropa>

    //Utilizado para emitir un mensaje cuando el resultado fue exitoso.
    val callbackContenidoIntentExplicito =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
                result->
            if(result.resultCode == Activity.RESULT_OK){
                adaptadorGlobal.notifyDataSetChanged()
                mostrarSnackBar("Operación exitosa")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud_ropa)

        //Cambiamos la etiqueta por el nombre del diseñador en caso de que exista uno.

        val nombreDiseñador = intent.getStringExtra("nombreDiseniador")
        if(nombreDiseñador!=null){
            findViewById<TextView>(R.id.txt_nombreAux).setText("DISEÑADOR: $nombreDiseñador")
        }

        //Agregamos los registros a la lista.
        val listView = findViewById<ListView>(R.id.lv_ropa)
        val indiceDiseniador = intent.getIntExtra("posicionDiseniador", -1)
        val listaRopa:ArrayList<Ropa>

        /*
        if(indiceDiseniador!=-1){
            listaRopa = BaseDatosDiseniador.arregloDiseniador[indiceDiseniador].ropa
        }else{
            listaRopa = BaseDatosRopa.arregloRopa
        }
         */
        listaRopa = BaseDatosRopaSQLite.tablaRopa!!.listarRopa(1)

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listaRopa
        )

        listView.adapter = adaptador
        adaptadorGlobal = adaptador
        adaptador.notifyDataSetChanged()

        //Para ir a agregar ropa.

        val botonIrAgregarRopa = findViewById<Button>(R.id.btn_irAgregarRopa)
        botonIrAgregarRopa.setOnClickListener {
            val intentExplicito = Intent(this, AgregarRopa::class.java)
            intentExplicito.putExtra("posicionDiseniador",indiceDiseniador)
            callbackContenidoIntentExplicito.launch(intentExplicito)
        }


        registerForContextMenu(listView)
    }

    //Para mostrar el menú flotante
    var posicionItemSeleccionado = -1

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ){
        super.onCreateContextMenu(menu,v,menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu02, menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val posicion = info.position
        posicionItemSeleccionado = posicion
    }

    //Acciones para cada item del menú flotante
    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.m_borrarRopa -> {
                val indiceDiseniador = intent.getIntExtra("posicionDiseniador", -1)

                if(indiceDiseniador!=-1){
                    BaseDatosDiseniador.arregloDiseniador[indiceDiseniador].ropa.removeAt(posicionItemSeleccionado)
                }else{
                    BaseDatosRopa.eliminarRopa(posicionItemSeleccionado)
                }
                adaptadorGlobal.notifyDataSetChanged()

                return true
            }
            R.id.m_editarRopa ->{
                val intentE = Intent(this, EditarRopa::class.java)
                intentE.putExtra("posicionRopa", posicionItemSeleccionado)
                intentE.putExtra("posicionDiseniador", intent.getIntExtra("posicionDiseniador", -1))
                callbackContenidoIntentExplicito.launch(intentE)
                return true
            }
            else -> super.onContextItemSelected(item)
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