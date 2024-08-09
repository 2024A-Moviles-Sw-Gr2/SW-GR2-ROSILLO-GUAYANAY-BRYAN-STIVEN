package com.example.deber02_bsrg.baseDatos.ropa

import android.content.Context
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.deber02_bsrg.entidades.Ropa

class SQLiteRopaHelper(contexto: Context?):SQLiteOpenHelper(contexto, "bd", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val scriptCrearTablaRopa =
            """
                CREATE TABLE LALO(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre VARCHAR (50)
                )
            """.trimIndent()
        db?.execSQL(scriptCrearTablaRopa)
    }

    /*
precio FLOAT,
tendencia VARCHAR(100),
lanzamiento VARCHAR (100),
vidaUtils INTEGER
*/

    fun agregarRopa(
        nombre:String
    ):Boolean{
        val conexionEscritura = writableDatabase
        val valoresGuardar = ContentValues()
        valoresGuardar.put("nombre", "Chaleco")
        /*
        valoresGuardar.put("precio", 123.02f)
        valoresGuardar.put("tendencia", "true")
        valoresGuardar.put("lanzamiento", "02/02/2024")
        valoresGuardar.put("vidaUtil", 9)

         */

        val resultadoGuardar = conexionEscritura.insert("LALO", null, valoresGuardar)
        return resultadoGuardar.toInt() != -1

    }

    fun eliminarRopa(id: Int):Boolean{
        val conexionEscritura = writableDatabase
        val consultaEliminar = arrayOf(id.toString())
        val resultadoEliminacion = conexionEscritura.delete("Ropa", "id=?", consultaEliminar)
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

        val resultadoActualizar = conexionEscritura.update("Ropa", valoresActualizar, "id=?", consultaActualizar)
        conexionEscritura.close()

        return resultadoActualizar.toInt() != -1
    }

    fun listarRopa(idDiseniador: Int): ArrayList<Ropa> {
        val arregloRopa = ArrayList<Ropa>()
        /*
        var resultadoConsultaListar:Cursor

        val conexionLectura = readableDatabase
        var scriptConsulta =
            """
                SELECT * FROM ROPA
            """.trimIndent()

        resultadoConsultaListar = conexionLectura.rawQuery(scriptConsulta,null)


        val existeUno = resultadoConsultaListar.moveToFirst()

        if (existeUno){
            do {
                val ropa = Ropa(
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



         */
        return arregloRopa
    }





    /*
    if(idDiseniador!=-1){
        scriptConsulta +=
            """
                 where id = ?
            """.trimIndent()
        val consultaListar = arrayOf(idDiseniador.toString())
        resultadoConsultaListar = conexionLectura.rawQuery(scriptConsulta, consultaListar)
    }else{
        resultadoConsultaListar = conexionLectura.rawQuery(scriptConsulta,null)
    }

     */




    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

}