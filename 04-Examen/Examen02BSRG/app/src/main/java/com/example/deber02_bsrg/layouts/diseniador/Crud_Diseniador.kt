package com.example.deber02_bsrg.layouts.diseniador

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
import androidx.activity.result.contract.ActivityResultContracts
import com.example.deber02_bsrg.layouts.ropa.Crud_Ropa
import com.example.deber02_bsrg.R
import com.example.deber02_bsrg.baseDatos.diseniador.TablaDiseniador
import com.example.deber02_bsrg.entidades.Diseniador
import com.example.deber02_bsrg.layouts.maps.GoogleMapsActivity
import com.google.android.material.snackbar.Snackbar

class Crud_Diseniador : AppCompatActivity() {

    var listaDiseniador:ArrayList<Diseniador> = ArrayList<Diseniador>()
    lateinit var adaptadorGlobal:ArrayAdapter<Diseniador>
    val tablaDiseniador = TablaDiseniador(this)

    val callbackContenidoIntentExplicito =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
                result->
            if(result.resultCode == Activity.RESULT_OK){
                if(result.data != null){
                    actualizarListaElementos()
                    adaptadorGlobal.notifyDataSetChanged()
                    mostrarSnackBar("Operación exitosa")
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud_diseniador)


        actualizarListaElementos()

        val botonIrAgregar = findViewById<Button>(R.id.btn_irAgregar)
        botonIrAgregar.setOnClickListener {
            val intentExplicito = Intent(this, AgregarDiseniador::class.java)
            callbackContenidoIntentExplicito.launch(intentExplicito)
        }

        val listView = findViewById<ListView>(R.id.lv_diseniador)
        registerForContextMenu(listView)
    }

    fun actualizarListaElementos(){
        val listView = findViewById<ListView>(R.id.lv_diseniador)

        listaDiseniador = tablaDiseniador.listarDiseniador()

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listaDiseniador
        )

        listView.adapter = adaptador
        adaptadorGlobal = adaptador
        adaptador.notifyDataSetChanged()
    }


    var posicionItemSeleccionado = -1

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ){
        super.onCreateContextMenu(menu,v,menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val posicion = info.position
        posicionItemSeleccionado = posicion
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.m_borrarDiseniador -> {
                tablaDiseniador.eliminarDiseniador(
                    tablaDiseniador.listarDiseniador()[posicionItemSeleccionado].id
                )
                actualizarListaElementos()
                adaptadorGlobal.notifyDataSetChanged()
                return true
            }
            R.id.m_editarDiseniador ->{
                val intent = Intent(this, EditarDiseniador::class.java)
                intent.putExtra("posicionDiseniador", posicionItemSeleccionado)
                callbackContenidoIntentExplicito.launch(intent)
                return true
            }
            R.id.m_verRopa ->{
                val intent = Intent(this, Crud_Ropa::class.java)
                intent.putExtra("id_diseniador",tablaDiseniador.listarDiseniador()[posicionItemSeleccionado].id)
                intent.putExtra("nombreDiseniador", tablaDiseniador.listarDiseniador()[posicionItemSeleccionado].nombre)
                startActivity(intent)
                return true
            }
            R.id.m_verUbicacion ->{
                val intent = Intent(this, GoogleMapsActivity::class.java)
                intent.putExtra("posicionDiseniador",posicionItemSeleccionado)
                intent.putExtra("nombreDiseniador", tablaDiseniador.listarDiseniador()[posicionItemSeleccionado].nombre)
                startActivity(intent)
                return true
            }


            else -> super.onContextItemSelected(item)
        }
    }

    fun mostrarSnackBar(texto:String){
        val snack = Snackbar.make(
            findViewById(R.id.mainDiseniadorLayout),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()

        Handler(Looper.getMainLooper()).postDelayed({
            snack.dismiss()
        }, 4000)
    }

}