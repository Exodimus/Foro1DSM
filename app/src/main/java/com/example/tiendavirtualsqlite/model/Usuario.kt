package com.example.tiendavirtualsqlite.model
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.tiendavirtualsqlite.db.HelperDB

class Usuario(context: Context) {
    private var helper: HelperDB? = null
    private var db: SQLiteDatabase? = null

    init {
        helper = HelperDB(context)
        db = helper!!.writableDatabase
    }

    companion object {
        // Nombre de la tabla
        const val TABLE_NAME_USUARIO = "usuario"

        // Nombre de los campos de la tabla usuario
        const val COL_ID_USUARIO = "idusuario"
        private const val COL_NOMBRE_USUARIO = "nombreusuario"
        private const val COL_PASSWORD = "password"

        // Sentencia SQL para crear la tabla usuario
        const val CREATE_TABLE_USUARIO = (
                "CREATE TABLE IF NOT EXISTS $TABLE_NAME_USUARIO ("
                        + "$COL_ID_USUARIO INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + "$COL_NOMBRE_USUARIO TEXT NOT NULL,"
                        + "$COL_PASSWORD TEXT NOT NULL"
                        + ")"
                )
    }

    //Métodos
}