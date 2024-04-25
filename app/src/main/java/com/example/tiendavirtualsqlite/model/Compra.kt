package com.example.tiendavirtualsqlite.model
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.tiendavirtualsqlite.db.HelperDB

class Compra(context: Context) {
    private var helper: HelperDB? = null
    private var db: SQLiteDatabase? = null

    init {
        helper = HelperDB(context)
        db = helper!!.writableDatabase
    }

    companion object {
        const val TABLE_NAME_COMPRA = "compra"

        const val COL_ID_COMPRA = "idcompra"
        private const val COL_ID_USUARIO = "idusuario"
        private const val COL_FECHA = "fecha"
        private const val COL_ESTADO = "estado"

        const val CREATE_TABLE_COMPRA = (
                "CREATE TABLE IF NOT EXISTS $TABLE_NAME_COMPRA ("
                        + "$COL_ID_COMPRA INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + "$COL_ID_USUARIO INTEGER,"
                        + "$COL_FECHA Timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,"
                        + "$COL_ESTADO INTEGER DEFAULT 0,"
                        + "FOREIGN KEY($COL_ID_USUARIO) REFERENCES ${Usuario.TABLE_NAME_USUARIO}(${Usuario.COL_ID_USUARIO})"
                        + ")"
                )
    }
    //Métodos
    fun crearCompra(idUsuario: Long, estadoInicial: Long): Long {
        val values = ContentValues().apply {
            put(COL_ID_USUARIO, idUsuario)
        //Fecha y estado tienen valores predeterminados desde la db
        }
        return db!!.insert(TABLE_NAME_COMPRA, null, values)
    }
    fun finalizarCompra(idUsuario: Long, idCompra: Long, estadoInicial: Long): Int {
        val values = ContentValues().apply {
            put(COL_ESTADO, 1)
        }
        return db!!.update(
            TABLE_NAME_COMPRA,
            values,
            "$COL_ID_USUARIO = ? AND $COL_ID_COMPRA = ?",
            arrayOf(idUsuario.toString(), idCompra.toString())
        )
    }
    fun eliminarCompra(idUsuario: Long): Int {
        return db!!.delete(TABLE_NAME_COMPRA, "$COL_ID_USUARIO = ?", arrayOf(idUsuario.toString()))
    }



}