package com.example.tiendavirtualsqlite.model
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.tiendavirtualsqlite.classes.ShoppingDetail
import com.example.tiendavirtualsqlite.db.HelperDB

class DetalleCompra(context: Context) {
    private var helper: HelperDB? = null
    private var db: SQLiteDatabase? = null

    init {
        helper = HelperDB(context)
        db = helper!!.writableDatabase
    }

    companion object {
        private const val TABLE_NAME_DETALLE_COMPRA = "detallecompra"

        private const val COL_ID_DETALLE_COMPRA = "iddetallecompra"
        private const val COL_ID_COMPRA = "idcompra"
        private const val COL_ID_PRODUCTO = "idproducto"
        private const val COL_CANTIDAD = "cantidad"

        const val CREATE_TABLE_DETALLE_COMPRA = (
                "CREATE TABLE IF NOT EXISTS $TABLE_NAME_DETALLE_COMPRA ("
                        + "$COL_ID_DETALLE_COMPRA INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + "$COL_ID_COMPRA INTEGER,"
                        + "$COL_ID_PRODUCTO INTEGER,"
                        + "$COL_CANTIDAD INTEGER,"
                        + "FOREIGN KEY($COL_ID_COMPRA) REFERENCES ${Compra.TABLE_NAME_COMPRAS}(${Compra.COL_ID_COMPRA}),"
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
        return db!!.insert(TABLE_NAME_DETALLE_COMPRA, null, values)
    }

    fun cambiarCantidad(idCompraProducto: Long, nuevaCantidad: Int): Int {
        val values = ContentValues().apply {
            put(COL_CANTIDAD, nuevaCantidad)
        }
        return db!!.update(TABLE_NAME_DETALLE_COMPRA, values, "$COL_ID_DETALLE_COMPRA = ?", arrayOf(idCompraProducto.toString()))
    }

    fun eliminarProducto(idCompraProducto: Long): Int {
        return db!!.delete(TABLE_NAME_DETALLE_COMPRA, "$COL_ID_DETALLE_COMPRA = ?", arrayOf(idCompraProducto.toString()))
    }
    fun verCompra(idCompra: Long): List<ShoppingDetail>? {val products = mutableListOf<ShoppingDetail>()
        val columns = arrayOf(
            DetalleCompra.COL_ID_DETALLE_COMPRA,
            DetalleCompra.COL_ID_COMPRA,
            DetalleCompra.COL_ID_PRODUCTO,
            DetalleCompra.COL_CANTIDAD
        )
        val cursor = db!!.query(
            TABLE_NAME_DETALLE_COMPRA,
            null,
            "$COL_ID_COMPRA = ?",
            arrayOf(idCompra.toString()),
            null,
            null,
            null
        )
        while (cursor.moveToNext()) {
            val idDetalleCompra = cursor.getLong(cursor.getColumnIndexOrThrow(DetalleCompra.COL_ID_DETALLE_COMPRA))
            val idProducto = cursor.getLong(cursor.getColumnIndexOrThrow(DetalleCompra.COL_ID_PRODUCTO))
            val idCompra = cursor.getLong(cursor.getColumnIndexOrThrow(DetalleCompra.COL_ID_COMPRA))
            val cantidad = cursor.getInt(cursor.getColumnIndexOrThrow(DetalleCompra.COL_CANTIDAD))
            val product = ShoppingDetail(idDetalleCompra, idCompra, idProducto, cantidad)
            if(cantidad > 0) {
                products.add(product)
            }
        }
        print(products)

        cursor.close()
        return products
    }
}