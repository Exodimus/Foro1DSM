package com.example.tiendavirtualsqlite.model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.tiendavirtualsqlite.db.HelperDB

class Producto(context: Context?) {

    private var helper: HelperDB? = null
    private var db: SQLiteDatabase? = null

    init {
        helper = HelperDB(context)
        db = helper!!.writableDatabase
    }

    companion object {
        //TABLA PRODUCTOS
        const val TABLE_NAME_PRODUCTOS = "productos"

        //nombre de los campos de la tabla contactos
        const val COL_ID = "idproducto"
        const val COL_DESCRIPCION = "descripcion"
        const val COL_PRECIO = "precio"
        const val COL_CANTIDAD = "cantidad"


        //sentencia SQL para crear la tabla.
        const val CREATE_TABLE_PRODUCTOS = (
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_PRODUCTOS + "("
                        + COL_ID + " integer primary key autoincrement,"
                        + COL_DESCRIPCION + " varchar(150) NOT NULL,"
                        + COL_PRECIO + " decimal(10,2) NOT NULL,"
                        + COL_CANTIDAD + " integer)"
                )
    }

    // Mostrar un registro particular
    fun searchProducto(id: Int): Cursor? {
        val columns = arrayOf(COL_ID, COL_DESCRIPCION, COL_PRECIO, COL_CANTIDAD)
        return db!!.query(
            TABLE_NAME_PRODUCTOS, columns,
            "$COL_ID=?", arrayOf(id.toString()), null, null, null
        )
    }

    // Mostrar todos los registros
    fun searchProductosAll(): Cursor? {
        val columns = arrayOf(COL_DESCRIPCION, COL_PRECIO, COL_CANTIDAD)
        return db!!.query(
            TABLE_NAME_PRODUCTOS, columns,
            null, null, null, null, "$COL_DESCRIPCION ASC"
        )
    }

    fun verCantidad(idProducto: Long): Int {
        val query = "SELECT $COL_CANTIDAD FROM $TABLE_NAME_PRODUCTOS WHERE $COL_ID = ?"
        val cursor = db?.rawQuery(query, arrayOf(idProducto.toString()))
        var cantidad = 0
        cursor?.use {
            if (it.moveToFirst()) {
                cantidad = it.getInt(it.getColumnIndexOrThrow(COL_CANTIDAD))
            }
        }
        return cantidad
    }

    fun actualizarCantidad(idProducto: Long, nuevaCantidad: Int): Int {
        val values = ContentValues().apply {
            put(COL_CANTIDAD, nuevaCantidad)
        }
        return db?.update(TABLE_NAME_PRODUCTOS, values, "$COL_ID = ?", arrayOf(idProducto.toString())) ?: 0
    }

    fun verDetalle(idProducto: Long): Triple<String?, Double?, Int?> {
        val query = "SELECT $COL_DESCRIPCION, $COL_PRECIO, $COL_CANTIDAD FROM $TABLE_NAME_PRODUCTOS WHERE $COL_ID = ?"
        val cursor = db!!.rawQuery(query, arrayOf(idProducto.toString()))
        var detalle: Triple<String?, Double?, Int?> = Triple(null, null, null)
        cursor?.use {
            if (it.moveToFirst()) {
                val nombre = it.getString(it.getColumnIndexOrThrow(COL_DESCRIPCION))
                val precio = it.getDouble(it.getColumnIndexOrThrow(COL_PRECIO))
                val cantidad = it.getInt(it.getColumnIndexOrThrow(COL_CANTIDAD))
                detalle = Triple(nombre, precio, cantidad)
            }
        }
        return detalle
    }
}