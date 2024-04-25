package com.example.tiendavirtualsqlite.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.tiendavirtualsqlite.model.Compra
import com.example.tiendavirtualsqlite.model.Producto
import com.example.tiendavirtualsqlite.model.Usuario
import com.example.tiendavirtualsqlite.model.CompraProducto

class HelperDB(context: Context?) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        private const val DB_NAME = "tiendavirtual.sqlite"
        private const val DB_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(Usuario.CREATE_TABLE_USUARIO)
        db.execSQL(Producto.CREATE_TABLE_PRODUCTOS)
        db.execSQL(Compra.CREATE_TABLE_COMPRA)
        db.execSQL(CompraProducto.CREATE_TABLE_COMPRA_PRODUCTO)
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}

}