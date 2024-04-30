package com.example.tiendavirtualsqlite.model
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.tiendavirtualsqlite.classes.Product
import com.example.tiendavirtualsqlite.classes.ShoppingCart
import com.example.tiendavirtualsqlite.db.HelperDB

class Compra(context: Context) {
    private var helper: HelperDB? = null
    private var db: SQLiteDatabase? = null

    init {
        helper = HelperDB(context)
        db = helper!!.writableDatabase
    }

    companion object {
        const val TABLE_NAME_COMPRAS = "compras"

        const val COL_ID_COMPRA = "idcompra"
        private const val COL_ID_USUARIO = "idusuario"
        private const val COL_FECHA = "fecha"
        private const val COL_ESTADO = "estado"

        const val CREATE_TABLE_COMPRAS = (
                "CREATE TABLE IF NOT EXISTS $TABLE_NAME_COMPRAS ("
                        + "$COL_ID_COMPRA INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + "$COL_ID_USUARIO INTEGER,"
                        + "$COL_FECHA Timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,"
                        + "$COL_ESTADO INTEGER DEFAULT 0,"
                        + "FOREIGN KEY($COL_ID_USUARIO) REFERENCES ${Usuario.TABLE_NAME_USUARIOS}(${Usuario.COL_ID_USUARIO})"
                        + ")"
                )
    }
    //Métodos
    fun crearCompra(idUsuario: Long): Long {
        val values = ContentValues().apply {
            put(COL_ID_USUARIO, idUsuario)
        //Fecha y estado tienen valores predeterminados desde la db
        }
        return db!!.insert(TABLE_NAME_COMPRAS, null, values)
    }
    fun finalizarCompra (idCompra: Long): Int {
        val values = ContentValues().apply {
            put(COL_ESTADO, 1)
        }
        return db!!.update(
            TABLE_NAME_COMPRAS,
            values,
            "$COL_ID_COMPRA = ?",
            arrayOf(idCompra.toString())
        )
    }
    fun eliminarCompra(idUsuario: Long): Int {
        return db!!.delete(TABLE_NAME_COMPRAS, "$COL_ID_USUARIO = ?", arrayOf(idUsuario.toString()))
    }

    fun obtenerCompraActiva(idUsuario: Long): Long {
        val columns = arrayOf(
            COL_ID_COMPRA,
            COL_ID_USUARIO,
            COL_FECHA,
            COL_ESTADO
        )
        val cursor = db!!.query(
            TABLE_NAME_COMPRAS, columns,
            "${COL_ID_USUARIO}=? AND ${COL_ESTADO}=?", arrayOf(idUsuario.toString(), "0"), null, null, null
        )

        var productId = -1L

        if (cursor.moveToFirst()) {
            productId = cursor.getLong(cursor.getColumnIndexOrThrow(COL_ID_COMPRA))
        }

        cursor.close()

        return productId
    }

    fun obtenerComprasFinalizadas(idUsuario: Long): List<ShoppingCart> {
        val shoppings = mutableListOf<ShoppingCart>()
        val columns = arrayOf(
            COL_ID_COMPRA,
            COL_ID_USUARIO,
            COL_FECHA,
            COL_ESTADO
        )
        val cursor = db!!.query(
            TABLE_NAME_COMPRAS, columns,
            "${COL_ID_USUARIO}=? AND ${COL_ESTADO}=?", arrayOf(idUsuario.toString(), "1"), null, null, null
        )

        while (cursor.moveToNext()) {
            val fecha = cursor.getString(cursor.getColumnIndexOrThrow(COL_FECHA))
            val idCompra = cursor.getLong(cursor.getColumnIndexOrThrow(COL_ID_COMPRA))
            val idUser = cursor.getLong(cursor.getColumnIndexOrThrow(COL_ID_USUARIO))

            val shop = ShoppingCart(fecha, idCompra, idUser)

            shoppings.add(shop)
        }

        cursor.close()
        return shoppings
    }
}