package com.example.tiendavirtualsqlite.model
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.tiendavirtualsqlite.db.HelperDB

class CompraProducto(context: Context) {
    private var helper: HelperDB? = null
    private var db: SQLiteDatabase? = null

    init {
        helper = HelperDB(context)
        db = helper!!.writableDatabase
    }

    companion object {
        private const val TABLE_NAME_COMPRA_PRODUCTO = "compra_producto"

        private const val COL_ID_COMPRA_PRODUCTO = "idcompraproducto"
        private const val COL_ID_COMPRA = "idcompra"
        private const val COL_ID_PRODUCTO = "idproducto"
        private const val COL_CANTIDAD = "cantidad"

        const val CREATE_TABLE_COMPRA_PRODUCTO = (
                "CREATE TABLE IF NOT EXISTS $TABLE_NAME_COMPRA_PRODUCTO ("
                        + "$COL_ID_COMPRA_PRODUCTO INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + "$COL_ID_COMPRA INTEGER,"
                        + "$COL_ID_PRODUCTO INTEGER,"
                        + "$COL_CANTIDAD INTEGER,"
                        + "FOREIGN KEY($COL_ID_COMPRA) REFERENCES ${Compra.TABLE_NAME_COMPRA}(${Compra.COL_ID_COMPRA}),"
                        + "FOREIGN KEY($COL_ID_PRODUCTO) REFERENCES ${Producto.TABLE_NAME_PRODUCTOS}(${Producto.COL_ID})"
                        + ")"
                )
    }

    fun agregarProducto(idCompra: Long, idProducto: Long, cantidad: Int): Long {
        val values = ContentValues().apply {
            put(COL_ID_COMPRA, idCompra)
            put(COL_ID_PRODUCTO, idProducto)
            put(COL_CANTIDAD, cantidad)
        }
        return db!!.insert(TABLE_NAME_COMPRA_PRODUCTO, null, values)
    }

    fun cambiarCantidad(idCompraProducto: Long, nuevaCantidad: Int): Int {
        val values = ContentValues().apply {
            put(COL_CANTIDAD, nuevaCantidad)
        }
        return db!!.update(TABLE_NAME_COMPRA_PRODUCTO, values, "$COL_ID_COMPRA_PRODUCTO = ?", arrayOf(idCompraProducto.toString()))
    }

    fun eliminarProducto(idCompraProducto: Long): Int {
        return db!!.delete(TABLE_NAME_COMPRA_PRODUCTO, "$COL_ID_COMPRA_PRODUCTO = ?", arrayOf(idCompraProducto.toString()))
    }
    fun verCompra(idCompra: Long): Cursor? {
        return db!!.query(
            TABLE_NAME_COMPRA_PRODUCTO,
            null,
            "$COL_ID_COMPRA = ?",
            arrayOf(idCompra.toString()),
            null,
            null,
            null
        )
    }
}
}