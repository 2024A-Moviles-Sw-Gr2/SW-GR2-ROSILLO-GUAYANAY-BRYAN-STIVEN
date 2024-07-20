package com.example.deber02_bsrg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import java.time.LocalDate

class EditarRopa : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_ropa)

        //Obtenemos el objeto ropa de la lista del diseñador, o de la lista global.
        val posicionItemRopa = intent.getIntExtra("posicionRopa",0)
        val indiceDiseniador = intent.getIntExtra("posicionDiseniador",-1)
        val objetoRopaEditar:Ropa

        if(indiceDiseniador!=-1){
            objetoRopaEditar = BaseDatosDiseniador.arregloDiseniador[indiceDiseniador].ropa[posicionItemRopa]
        }else{
            objetoRopaEditar = BaseDatosRopa.arregloRopa[posicionItemRopa]
        }


        //Del objeto rescatamos sus datos, y los enviamos a la actividad de actualizar.
        findViewById<EditText>(R.id.txt_nombreRopaActualizar).setText(objetoRopaEditar.nombre)
        findViewById<EditText>(R.id.txt_precioRopaActualizar).setText(objetoRopaEditar.precio.toString())
        findViewById<EditText>(R.id.txt_tendenciaRopaActualizar).setText(objetoRopaEditar.tendencia.toString())

        val fechaActualizar = "${objetoRopaEditar.lanzamiento.dayOfMonth}/${objetoRopaEditar.lanzamiento.monthValue}/${objetoRopaEditar.lanzamiento.year}"
        findViewById<EditText>(R.id.txt_lanzamientoRopaActualizar).setText(fechaActualizar)
        findViewById<EditText>(R.id.txt_vidaUtilRopaActualizar).setText(objetoRopaEditar.aniosVidaUtil.toString())

        //Nuevamente, rescatamos los datos de la interfaz, y creamos un nuevo objeto.
        val botonActualizarRopa = findViewById<Button>(R.id.btn_actualizarRopa)
        botonActualizarRopa.setOnClickListener {
            val nombre = findViewById<EditText>(R.id.txt_nombreRopaActualizar).text.toString()
            val precio = findViewById<EditText>(R.id.txt_precioRopaActualizar).text.toString().toFloat()
            val tendencia = findViewById<EditText>(R.id.txt_tendenciaRopaActualizar).text.toString().toBoolean()
            val arregloFechaLanzamiento = findViewById<EditText>(R.id.txt_lanzamientoRopaActualizar).text.toString().split("/")
            val fechaLanzamiento = LocalDate.of(arregloFechaLanzamiento[2].toInt(),arregloFechaLanzamiento[1].toInt(),arregloFechaLanzamiento[0].toInt())
            val vidaUtil = findViewById<EditText>(R.id.txt_vidaUtilRopaActualizar).text.toString().toInt()

            val nuevaRopa = Ropa(
                nombre,
                precio,
                tendencia,
                fechaLanzamiento,
                vidaUtil
            )


            //Hacemos los cambios en la lista global de ropa, y en el del diseñador si existe.
            if(indiceDiseniador!=-1){
                var indice = 0
                for(ropa in BaseDatosRopa.arregloRopa){
                    if(!ropa.equals(BaseDatosDiseniador.arregloDiseniador[indiceDiseniador].ropa[posicionItemRopa])){
                        indice++
                    }else{
                        break
                    }
                }

                BaseDatosDiseniador.arregloDiseniador[indiceDiseniador].ropa[posicionItemRopa] = nuevaRopa
                BaseDatosRopa.arregloRopa[indice] = nuevaRopa
            }else{
                BaseDatosRopa.arregloRopa[posicionItemRopa] = nuevaRopa
            }


            devolverRespuesta()
        }
    }

    fun devolverRespuesta(){
        val intentDevolverRespuesta = Intent()
        setResult(RESULT_OK, intentDevolverRespuesta)
        finish()
    }
}