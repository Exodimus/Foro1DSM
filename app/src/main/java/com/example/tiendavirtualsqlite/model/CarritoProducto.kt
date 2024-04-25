package com.example.tiendavirtualsqlite.model
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.tiendavirtualsqlite.db.HelperDB

class CarritoProducto(context: Context) {
    private var helper: HelperDB? = null
    private var db: SQLiteDatabase? = null

    init {
        helper = HelperDB(context)
        db = helper!!.writableDatabase
    }

    companion object {
        private const val TABLE_NAME_CARRITO_PRODUCTO = "carrito_producto"

        private const val COL_ID_CARRITO_PRODUCTO = "idcarritoproducto"
        private const val COL_ID_CARRITO = "idcarrito"
        private const val COL_ID_PRODUCTO = "idproducto"
        private const val COL_CANTIDAD = "cantidad"

        const val CREATE_TABLE_CARRITO_PRODUCTO = (
                "CREATE TABLE IF NOT EXISTS $TABLE_NAME_CARRITO_PRODUCTO ("
                        + "$COL_ID_CARRITO_PRODUCTO INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + "$COL_ID_CARRITO INTEGER,"
                        + "$COL_ID_PRODUCTO INTEGER,"
                        + "$COL_CANTIDAD INTEGER,"
                        + "FOREIGN KEY($COL_ID_CARRITO) REFERENCES ${Carrito.TABLE_NAME_CARRITO}(${Carrito.COL_ID_CARRITO}),"
                        + "FOREIGN KEY($COL_ID_PRODUCTO) REFERENCES ${Producto.TABLE_NAME_PRODUCTOS}(${Producto.COL_ID})"
                        + ")"
                )
    }

    //Métodos
}