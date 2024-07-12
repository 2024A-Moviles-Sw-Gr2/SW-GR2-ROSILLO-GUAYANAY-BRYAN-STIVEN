package com.example.a2024aswgr2bsrg

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelperEntrenador(
    contexto: Context?
): SQLiteOpenHelper(
    contexto,
    "moviles",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaEntrenador =
            """
                CREATE TABLE ENTRENADOR(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre VARCHAR(50),
                    descripcion VARCHAR(50)
                )
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaEntrenador)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun crearEntrenador(
        nombre:String,
        descripcion:String
    ):Boolean{
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("descripcion", descripcion)
        val resultadoGuardar = basedatosEscritura.insert(
            "ENTRENADOR",
            null,
            valoresAGuardar
        )
        basedatosEscritura.close()
        return if(resultadoGuardar.toInt()==-1) false else true
    }


    fun eliminarEntrenadorFormulario(id:Int):Boolean{
        val conexionEscritura = writableDatabase

        val parametrisConsultaDelete = arrayOf(id.toString())
        val resultadoEliminacion = conexionEscritura
            .delete(
                "ENTRENADOR",
                "id=?",
                parametrisConsultaDelete
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt()==-1) false else true
    }


    fun actualizarEntrenadorFormulario(nombre:String, descripcion: String, id: Int):Boolean{
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("descripcion", descripcion)

        val parametrosConsultarActualizar = arrayOf(id.toString())
        val resultadoActualizacion = conexionEscritura
            .update(
                "ENTRENADOR",
                valoresAActualizar,
                "id=?",
                parametrosConsultarActualizar
            )
        conexionEscritura.close()
        return if(resultadoActualizacion.toInt()==-1) false else true
    }

    fun consultarEntrenadorPorId(id:Int):BEntrenador?{
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura =
            """
                SELECT * FROM ENTRENADOR WHERE ID = ?
            """.trimIndent()
        val arregloParametrosConsultaLectura = arrayOf(id.toString())
        val resultadoConsultaLectura = baseDatosLectura
            .rawQuery(
                scriptConsultaLectura,
                arregloParametrosConsultaLectura
            )

        //Lógica de búsqueda
        // Recibimos un arreglo (puede ser nulo)
        // Llenar un arreglo de entreandores
        //Si esta vacio, el arreglo no tiene elementos

        val existeAlMenos = resultadoConsultaLectura.moveToFirst()
        val arregloRespuesta = arrayListOf<BEntrenador>()
        if(existeAlMenos){
            do{
                val entrenador = BEntrenador(
                    resultadoConsultaLectura.getInt(0),
                    resultadoConsultaLectura.getString(1),
                    resultadoConsultaLectura.getString(2)
                )
                arregloRespuesta.add(entrenador)
            }while(resultadoConsultaLectura.moveToNext())
        }

        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return if(arregloRespuesta.size > 0) arregloRespuesta[0] else null
    }


}