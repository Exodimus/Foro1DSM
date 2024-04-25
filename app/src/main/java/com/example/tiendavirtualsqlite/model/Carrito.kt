package com.example.tiendavirtualsqlite.model
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.tiendavirtualsqlite.db.HelperDB

class Carrito(context: Context) {
    private var helper: HelperDB? = null
    private var db: SQLiteDatabase? = null

    init {
        helper = HelperDB(context)
        db = helper!!.writableDatabase
    }

    companion object {
        const val TABLE_NAME_CARRITO = "carrito"

        const val COL_ID_CARRITO = "idcarrito"
        private const val COL_ID_USUARIO = "idusuario"

        const val CREATE_TABLE_CARRITO = (
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_CARRITO + "("
                        + COL_ID_CARRITO + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + COL_ID_USUARIO + " INTEGER,"
                        + "FOREIGN KEY(" + COL_ID_USUARIO + ") REFERENCES " + Usuario.TABLE_NAME_USUARIO + "(" + Usuario.COL_ID_USUARIO + ")"
                        + ")"
                )
    }

    //Métodos
}