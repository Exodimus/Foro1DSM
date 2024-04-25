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
    fun signup(nombreUsuario: String, password: String): Long {
        val values = ContentValues().apply {
            put(COL_NOMBRE_USUARIO, nombreUsuario)
            put(COL_PASSWORD, password)
        }
        return db!!.insert(TABLE_NAME_USUARIO, null, values)
    }

    fun login(nombreUsuario: String, password: String): Boolean {
        val cursor = db!!.query(
            TABLE_NAME_USUARIO,
            null,
            "$COL_NOMBRE_USUARIO = ? AND $COL_PASSWORD = ?",
            arrayOf(nombreUsuario, password),
            null,
            null,
            null
        )
        val count = cursor.count
        cursor.close()
        return count > 0
    }
}