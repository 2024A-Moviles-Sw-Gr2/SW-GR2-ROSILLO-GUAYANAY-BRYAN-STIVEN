package com.example.deber02_bsrg.baseDatos

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.deber02_bsrg.entidades.Diseniador
import com.example.deber02_bsrg.entidades.Ropa

open class SQLiteHelper(contexto: Context?): SQLiteOpenHelper(contexto, "onebd", null, 1)  {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptCrearTablaRopa =
            """
                CREATE TABLE ROPA(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre VARCHAR (50),
                    precio FLOAT,
                    tendencia VARCHAR(50),
                    lanzamiento VARCHAR (50),
                    vidaUtil INTEGER,
                    id_diseniador INTEGER,
                    FOREIGN KEY (id_diseniador) REFERENCES DISENIADOR(id)
                )
            """.trimIndent()
        db?.execSQL(scriptCrearTablaRopa)

        val scriptCrearTablaDiseniador =
            """
                CREATE TABLE DISENIADOR(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre VARCHAR (50),
                    colecciones INTEGER,
                    unisex VARCHAR (50),
                    ubicacion VARCHAR (50)
                )
            """.trimIndent()
        db?.execSQL(scriptCrearTablaDiseniador)

    }

    override fun onOpen(db: SQLiteDatabase?) {
        super.onOpen(db)
        db?.execSQL("PRAGMA foreign_keys = ON")
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}