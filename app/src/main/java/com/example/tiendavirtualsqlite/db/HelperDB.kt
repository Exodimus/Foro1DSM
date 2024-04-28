package com.example.tiendavirtualsqlite.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.tiendavirtualsqlite.Product
import com.example.tiendavirtualsqlite.model.Compra
import com.example.tiendavirtualsqlite.model.Producto
import com.example.tiendavirtualsqlite.model.Usuario
import com.example.tiendavirtualsqlite.model.DetalleCompra
import org.json.JSONArray

class HelperDB(context: Context?) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        private const val DB_NAME = "tiendavirtual.sqlite"
        private const val DB_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(Usuario.CREATE_TABLE_USUARIOS)
        db.execSQL(Producto.CREATE_TABLE_PRODUCTOS)
        db.execSQL(Compra.CREATE_TABLE_COMPRAS)
        db.execSQL(DetalleCompra.CREATE_TABLE_DETALLE_COMPRA)
        var productos = leerArchivoJSON()
        for (producto in productos) {
            val contentValues = ContentValues().apply {
                put(Producto.COL_DESCRIPCION, producto.descripcion)
                put(Producto.COL_PRECIO, producto.precio)
                put(Producto.COL_CANTIDAD, producto.cantidad)
                put(Producto.COL_IMG, producto.imagen)
                put(Producto.COL_NOMBRE, producto.nombre)
            }
            db.insert(Producto.TABLE_NAME_PRODUCTOS, null, contentValues)
        }
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}
    public fun leerArchivoJSON(): List<Product> {
        val productos = mutableListOf<Product>()

        val jsonArray = JSONArray("[{\"descripcion\":\"Andy shoes are designed to keeping in mind durability as well as trends, the most stylish range of shoes & sandals\",\"precio\":\"187.00\",\"cantidad\":28687,\"nombre\":\"Fish\",\"imagen\":\"https://loremflickr.com/640/480/food\",\"idproducto\":\"1\"},{\"descripcion\":\"The Apollotech B340 is an affordable wireless mouse with reliable connectivity, 12 months battery life and modern design\",\"precio\":\"329.00\",\"cantidad\":8576,\"nombre\":\"Keyboard\",\"imagen\":\"https://loremflickr.com/640/480/food\",\"idproducto\":\"2\"},{\"descripcion\":\"New ABC 13 9370, 13.3, 5th Gen CoreA5-8250U, 8GB RAM, 256GB SSD, power UHD Graphics, OS 10 Home, OS Office A & J 2016\",\"precio\":\"373.00\",\"cantidad\":52406,\"nombre\":\"Chips\",\"imagen\":\"https://loremflickr.com/640/480/food\",\"idproducto\":\"3\"},{\"descripcion\":\"The Football Is Good For Training And Recreational Purposes\",\"precio\":\"784.00\",\"cantidad\":68203,\"nombre\":\"Keyboard\",\"imagen\":\"https://loremflickr.com/640/480/food\",\"idproducto\":\"4\"},{\"descripcion\":\"The beautiful range of Apple Naturalé that has an exciting mix of natural ingredients. With the Goodness of 100% Natural Ingredients\",\"precio\":\"789.00\",\"cantidad\":55172,\"nombre\":\"Car\",\"imagen\":\"https://loremflickr.com/640/480/food\",\"idproducto\":\"5\"},{\"descripcion\":\"The Football Is Good For Training And Recreational Purposes\",\"precio\":\"795.00\",\"cantidad\":57891,\"nombre\":\"Chicken\",\"imagen\":\"https://loremflickr.com/640/480/food\",\"idproducto\":\"6\"},{\"descripcion\":\"The beautiful range of Apple Naturalé that has an exciting mix of natural ingredients. With the Goodness of 100% Natural Ingredients\",\"precio\":\"642.00\",\"cantidad\":96901,\"nombre\":\"Table\",\"imagen\":\"https://loremflickr.com/640/480/food\",\"idproducto\":\"7\"},{\"descripcion\":\"The Apollotech B340 is an affordable wireless mouse with reliable connectivity, 12 months battery life and modern design\",\"precio\":\"260.00\",\"cantidad\":79676,\"nombre\":\"Pizza\",\"imagen\":\"https://loremflickr.com/640/480/food\",\"idproducto\":\"8\"},{\"descripcion\":\"The Nagasaki Lander is the trademarked name of several series of Nagasaki sport bikes, that started with the 1984 ABC800J\",\"precio\":\"669.00\",\"cantidad\":59955,\"nombre\":\"Sausages\",\"imagen\":\"https://loremflickr.com/640/480/food\",\"idproducto\":\"9\"},{\"descripcion\":\"The slim & simple Maple Gaming Keyboard from Dev Byte comes with a sleek body and 7- Color RGB LED Back-lighting for smart functionality\",\"precio\":\"992.00\",\"cantidad\":16181,\"nombre\":\"Fish\",\"imagen\":\"https://loremflickr.com/640/480/food\",\"idproducto\":\"10\"},{\"descripcion\":\"Carbonite web goalkeeper gloves are ergonomically designed to give easy fit\",\"precio\":\"123.00\",\"cantidad\":22262,\"nombre\":\"Bike\",\"imagen\":\"https://loremflickr.com/640/480/food\",\"idproducto\":\"11\"},{\"descripcion\":\"The Football Is Good For Training And Recreational Purposes\",\"precio\":\"645.00\",\"cantidad\":55310,\"nombre\":\"Keyboard\",\"imagen\":\"https://loremflickr.com/640/480/food\",\"idproducto\":\"12\"},{\"descripcion\":\"New ABC 13 9370, 13.3, 5th Gen CoreA5-8250U, 8GB RAM, 256GB SSD, power UHD Graphics, OS 10 Home, OS Office A & J 2016\",\"precio\":\"635.00\",\"cantidad\":79163,\"nombre\":\"Gloves\",\"imagen\":\"https://loremflickr.com/640/480/food\",\"idproducto\":\"13\"},{\"descripcion\":\"Carbonite web goalkeeper gloves are ergonomically designed to give easy fit\",\"precio\":\"46.00\",\"cantidad\":41225,\"nombre\":\"Chicken\",\"imagen\":\"https://loremflickr.com/640/480/food\",\"idproducto\":\"14\"},{\"descripcion\":\"The Apollotech B340 is an affordable wireless mouse with reliable connectivity, 12 months battery life and modern design\",\"precio\":\"760.00\",\"cantidad\":34488,\"nombre\":\"Salad\",\"imagen\":\"https://loremflickr.com/640/480/food\",\"idproducto\":\"15\"},{\"descripcion\":\"The automobile layout consists of a front-engine design, with transaxle-type transmissions mounted at the rear of the engine and four wheel drive\",\"precio\":\"912.00\",\"cantidad\":42374,\"nombre\":\"Computer\",\"imagen\":\"https://loremflickr.com/640/480/food\",\"idproducto\":\"16\"},{\"descripcion\":\"Ergonomic executive chair upholstered in bonded black leather and PVC padded seat and back for all-day comfort and support\",\"precio\":\"697.00\",\"cantidad\":24296,\"nombre\":\"Soap\",\"imagen\":\"https://loremflickr.com/640/480/food\",\"idproducto\":\"17\"},{\"descripcion\":\"Boston's most advanced compression wear technology increases muscle oxygenation, stabilizes active muscles\",\"precio\":\"69.00\",\"cantidad\":44170,\"nombre\":\"Sausages\",\"imagen\":\"https://loremflickr.com/640/480/food\",\"idproducto\":\"18\"},{\"descripcion\":\"Boston's most advanced compression wear technology increases muscle oxygenation, stabilizes active muscles\",\"precio\":\"742.00\",\"cantidad\":45971,\"nombre\":\"Soap\",\"imagen\":\"https://loremflickr.com/640/480/food\",\"idproducto\":\"19\"},{\"descripcion\":\"Carbonite web goalkeeper gloves are ergonomically designed to give easy fit\",\"precio\":\"122.00\",\"cantidad\":25109,\"nombre\":\"Fish\",\"imagen\":\"https://loremflickr.com/640/480/food\",\"idproducto\":\"20\"},{\"descripcion\":\"The slim & simple Maple Gaming Keyboard from Dev Byte comes with a sleek body and 7- Color RGB LED Back-lighting for smart functionality\",\"precio\":\"245.00\",\"cantidad\":47493,\"nombre\":\"Car\",\"imagen\":\"https://loremflickr.com/640/480/food\",\"idproducto\":\"21\"},{\"descripcion\":\"The Football Is Good For Training And Recreational Purposes\",\"precio\":\"922.00\",\"cantidad\":61843,\"nombre\":\"Fish\",\"imagen\":\"https://loremflickr.com/640/480/food\",\"idproducto\":\"22\"}]")
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val producto = Product(
                descripcion = jsonObject.getString("descripcion"),
                precio = jsonObject.getDouble("precio"),
                cantidad = jsonObject.getInt("cantidad"),
                nombre = jsonObject.getString("nombre"),
                imagen = jsonObject.getString("imagen"),
                idproducto = jsonObject.getString("idproducto")
            )
            productos.add(producto)
        }
        return  productos
    }
}