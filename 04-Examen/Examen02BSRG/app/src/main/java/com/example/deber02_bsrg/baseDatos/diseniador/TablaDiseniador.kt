package com.example.deber02_bsrg.baseDatos.diseniador

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.deber02_bsrg.baseDatos.SQLiteHelper
import com.example.deber02_bsrg.entidades.Diseniador

class TablaDiseniador(contexto: Context?): SQLiteHelper(contexto) {

    fun agregarDiseniador(
        nombre: String,
        colecciones: Int,
        unisex: String,
        ubicacion: String
    ):Boolean{
        val conexionEscritura = writableDatabase
        val valoresGuardar = ContentValues()
        valoresGuardar.put("nombre", nombre)
        valoresGuardar.put("colecciones", colecciones)
        valoresGuardar.put("unisex", unisex)
        valoresGuardar.put("ubicacion", ubicacion)

        val resultadoGuardar = conexionEscritura.insert("DISENIADOR", null, valoresGuardar)
        return resultadoGuardar.toInt() != -1

    }

    fun eliminarDiseniador(id: Int):Boolean{
        val conexionEscritura = writableDatabase
        val consultaEliminar = arrayOf(id.toString())
        val resultadoEliminacion = conexionEscritura.delete("DISENIADOR", "id=?", consultaEliminar)
        conexionEscritura.close()
        return resultadoEliminacion.toInt() !=-1
    }

    fun actualizarDiseniador(
        id:Int,
        nombre: String,
        colecciones: Int,
        unisex: String,
        ubicacion: String
    ): Boolean{
        val conexionEscritura = writableDatabase
        val valoresActualizar = ContentValues()
        valoresActualizar.put("nombre", nombre)
        valoresActualizar.put("colecciones", colecciones)
        valoresActualizar.put("unisex", unisex)
        valoresActualizar.put("ubicacion", ubicacion)
        val consultaActualizar = arrayOf(id.toString())

        val resultadoActualizar = conexionEscritura.update("DISENIADOR", valoresActualizar, "id=?", consultaActualizar)
        conexionEscritura.close()

        return resultadoActualizar.toInt() != -1
    }


    fun listarDiseniador(): ArrayList<Diseniador> {
        val arregloDiseniador = ArrayList<Diseniador>()

        var resultadoConsultaListar: Cursor

        val conexionLectura = readableDatabase
        var scriptConsulta =
            """
                SELECT * FROM DISENIADOR
            """.trimIndent()

        resultadoConsultaListar = conexionLectura.rawQuery(scriptConsulta,null)


        val existeUno = resultadoConsultaListar.moveToFirst()

        if (existeUno){
            do {
                val diseniador = Diseniador(
                    resultadoConsultaListar.getInt(0),
                    resultadoConsultaListar.getString(1),
                    resultadoConsultaListar.getInt(2),
                    resultadoConsultaListar.getString(3),
                    resultadoConsultaListar.getString(4)
                )
                arregloDiseniador.add(diseniador)
            }while (resultadoConsultaListar.moveToNext())
        }
        resultadoConsultaListar.close()
        conexionLectura.close()
        return arregloDiseniador
    }

}