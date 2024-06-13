package com.example.a2024aswgr2bsrg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.google.android.material.snackbar.Snackbar

class ACicloVida : AppCompatActivity() {

    var textoGlobal = ""

    fun mostrarSackBar(texto:String){
        textoGlobal += texto
        val snack = Snackbar.make(
            findViewById(R.id.cl_ciclo_vida),
            textoGlobal,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aciclo_vida)
    }

    override fun onStart() {
        super.onStart()
        mostrarSackBar("OnStart")
    }

    override fun onResume() {
        super.onResume()
        mostrarSackBar("OnResume")
    }

    override fun onRestart() {
        super.onRestart()
        mostrarSackBar("OnRestart")
    }

    override fun onPause() {
        super.onPause()
        mostrarSackBar("OnPause")
    }

    override fun onStop() {
        super.onStop()
        mostrarSackBar("OnStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        mostrarSackBar("OnDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run{
            //Guardar primitivas
            putString("variableTextoGuardado", textoGlobal)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(
        savedInstanceState: Bundle
    ) {
        super.onRestoreInstanceState(savedInstanceState)

        //Recuperar las variables
        val textoRecuperadoDeVariable: String? = savedInstanceState.getString("variableTextoGuardado")
        if(textoRecuperadoDeVariable!=null){
            mostrarSackBar(textoRecuperadoDeVariable)
            textoGlobal = textoRecuperadoDeVariable
        }
    }






}