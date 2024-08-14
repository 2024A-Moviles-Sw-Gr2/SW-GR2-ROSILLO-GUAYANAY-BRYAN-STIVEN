package com.example.deber02_bsrg.baseDatos.ropa

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.deber02_bsrg.baseDatos.SQLiteHelper
import com.example.deber02_bsrg.entidades.Ropa

class TablaRopa(contexto: Context?): SQLiteHelper(contexto) {

    fun agregarRopa(
        nombre:String,
        precio:Float,
        tendencia:Boolean,
        lanzamiento: String,
        aniosVidaUtil: Int,
        id_diseniador: Int
    ):Boolean{
        val conexionEscritura = writableDatabase
        val valoresGuardar = ContentValues()
        valoresGuardar.put("nombre", nombre)
        valoresGuardar.put("precio", precio)
        valoresGuardar.put("tendencia", tendencia.toString())
        valoresGuardar.put("lanzamiento", lanzamiento)
        valoresGuardar.put("vidaUtil", aniosVidaUtil)

        if(id_diseniador!=-1){
            valoresGuardar.put("id_diseniador", id_diseniador)
        }

        val resultadoGuardar = conexionEscritura.insert("ROPA", null, valoresGuardar)
        return resultadoGuardar.toInt() != -1

    }

    fun eliminarRopa(id: Int):Boolean{
        val conexionEscritura = writableDatabase
        val consultaEliminar = arrayOf(id.toString())
        val resultadoEliminacion = conexionEscritura.delete("ROPA", "id=?", consultaEliminar)
        conexionEscritura.close()
        return resultadoEliminacion.toInt() !=-1
    }

    fun actualizarRopa(
        id:Int,
        nombre:String,
        precio:Float,
        tendencia:Boolean,
        lanzamiento: String,
        aniosVidaUtil: Int
    ): Boolean{
        val conexionEscritura = writableDatabase
        val valoresActualizar = ContentValues()
        valoresActualizar.put("nombre", nombre)
        valoresActualizar.put("precio", precio)
        valoresActualizar.put("tendencia", tendencia)
        valoresActualizar.put("lanzamiento", lanzamiento)
        valoresActualizar.put("vidaUtil", aniosVidaUtil)
        val consultaActualizar = arrayOf(id.toString())

        val resultadoActualizar = conexionEscritura.update("ROPA", valoresActualizar, "id=?", consultaActualizar)
        conexionEscritura.close()

        return resultadoActualizar.toInt() != -1
    }

    fun listarRopa(id_diseniador: Int): ArrayList<Ropa> {
        val arregloRopa = ArrayList<Ropa>()
        var resultadoConsultaListar: Cursor
        val conexionLectura = readableDatabase

        val scriptConsulta = if (id_diseniador != -1) {
            """
                SELECT * FROM ROPA
                WHERE id_diseniador = ?
            """.trimIndent()
        } else {
            """
                SELECT * FROM ROPA
            """.trimIndent()
        }

        resultadoConsultaListar = conexionLectura.rawQuery(scriptConsulta,if (id_diseniador != -1) arrayOf(id_diseniador.toString()) else null)

        val existeUno = resultadoConsultaListar.moveToFirst()

        if (existeUno){
            do {
                val ropa = Ropa(
                    resultadoConsultaListar.getInt(0),
                    resultadoConsultaListar.getString(1),
                    resultadoConsultaListar.getFloat(2),
                    resultadoConsultaListar.getString(3).toBoolean(),
                    resultadoConsultaListar.getString(4),
                    resultadoConsultaListar.getInt(5)
                )
                arregloRopa.add(ropa)
            }while (resultadoConsultaListar.moveToNext())
        }
        resultadoConsultaListar.close()
        conexionLectura.close()

        return arregloRopa
    }
}