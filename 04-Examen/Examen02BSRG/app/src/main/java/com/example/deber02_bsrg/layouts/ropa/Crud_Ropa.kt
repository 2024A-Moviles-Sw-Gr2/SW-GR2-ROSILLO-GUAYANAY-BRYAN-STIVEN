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
import com.example.deber02_bsrg.baseDatos.ropa.TablaRopa
import com.example.deber02_bsrg.entidades.Ropa
import com.google.android.material.snackbar.Snackbar

class Crud_Ropa : AppCompatActivity() {

    val tablaRopa = TablaRopa(this)
    var listaRopa:ArrayList<Ropa> = ArrayList<Ropa>()

    //Adaptador que sirve para ir refrescando la lista de ropa.
    lateinit var adaptadorGlobal:ArrayAdapter<Ropa>

    //Utilizado para emitir un mensaje cuando el resultado fue exitoso.
    val callbackContenidoIntentExplicito =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
                result->
            if(result.resultCode == Activity.RESULT_OK){
                val id_diseniador = intent.getIntExtra("id_diseniador", -1)
                actualizarListaElementos(id_diseniador)
                adaptadorGlobal.notifyDataSetChanged()
                mostrarSnackBar("Operación exitosa")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud_ropa)
        val listView = findViewById<ListView>(R.id.lv_ropa)

        //Cambiamos la etiqueta por el nombre del diseñador en caso de que exista uno.

        val nombreDiseniador = intent.getStringExtra("nombreDiseniador")
        if(nombreDiseniador!=null){
            findViewById<TextView>(R.id.txt_nombreAux).setText("DISEÑADOR: $nombreDiseniador")
        }


        val id_diseniador = intent.getIntExtra("id_diseniador", -1)
        actualizarListaElementos(id_diseniador)

        //Para ir a agregar ropa.

        val botonIrAgregarRopa = findViewById<Button>(R.id.btn_irAgregarRopa)
        botonIrAgregarRopa.setOnClickListener {
            val intentExplicito = Intent(this, AgregarRopa::class.java)
            intentExplicito.putExtra("id_diseniador",id_diseniador)
            callbackContenidoIntentExplicito.launch(intentExplicito)
        }


        registerForContextMenu(listView)
    }

    fun actualizarListaElementos(id_diseniador: Int){
        val listView = findViewById<ListView>(R.id.lv_ropa)

        listaRopa = tablaRopa.listarRopa(id_diseniador)

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listaRopa
        )

        listView.adapter = adaptador
        adaptadorGlobal = adaptador
        adaptador.notifyDataSetChanged()

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
                val id_diseniador = intent.getIntExtra("id_diseniador", -1)

                if(id_diseniador!=-1){
                    val id_ropaBorrar = tablaRopa.listarRopa(id_diseniador)[posicionItemSeleccionado].id
                    tablaRopa.eliminarRopa(id_ropaBorrar)
                }else{
                    tablaRopa.eliminarRopa(
                        tablaRopa.listarRopa(id_diseniador)[posicionItemSeleccionado].id
                    )
                    actualizarListaElementos(id_diseniador)
                }
                adaptadorGlobal.notifyDataSetChanged()

                return true
            }
            R.id.m_editarRopa ->{
                val intentE = Intent(this, EditarRopa::class.java)
                val id_diseniador = intent.getIntExtra("id_diseniador", -1)
                intentE.putExtra("posicionRopa", posicionItemSeleccionado)
                intentE.putExtra("id_diseniador", id_diseniador)
                callbackContenidoIntentExplicito.launch(intentE)
                actualizarListaElementos(id_diseniador)
                adaptadorGlobal.notifyDataSetChanged()
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