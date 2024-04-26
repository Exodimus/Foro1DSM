package com.example.tiendavirtualsqlite.model
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
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
        const val TABLE_NAME_USUARIOS = "usuario"

        // Nombre de los campos de la tabla usuario
        const val COL_ID_USUARIO = "idusuario"
        private const val COL_NOMBRE_USUARIO = "nombreusuario"
        private const val COL_PASSWORD = "password"

        // Sentencia SQL para crear la tabla usuario
        const val CREATE_TABLE_USUARIOS = (
                "CREATE TABLE IF NOT EXISTS $TABLE_NAME_USUARIOS ("
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
        //Retorna el ID si la insersión es exitosa
        return db!!.insert(TABLE_NAME_USUARIOS, null, values)
    }

    fun login(nombreUsuario: String, password: String): Long {
        val cursor = db!!.query(
            TABLE_NAME_USUARIOS,
            arrayOf(COL_ID_USUARIO),
            "$COL_NOMBRE_USUARIO = ? AND $COL_PASSWORD = ?",
            arrayOf(nombreUsuario, password),
            null,
            null,
            null
        )

        var userId: Long = -1 // Valor predeterminado si el inicio de sesión falla
        if (cursor.moveToFirst()) {
            val columnIndex = cursor.getColumnIndex(COL_ID_USUARIO)
            if (columnIndex >= 0) {
                userId = cursor.getLong(columnIndex)
            } else {
                Log.e("Usuario", "Columna $COL_ID_USUARIO no encontrada en el cursor")
            }
        } else {
            Log.e("Usuario", "No se encontraron resultados en el cursor")
        }
        cursor.close()
        return userId
    }
}