package com.example.tiendavirtualsqlite.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.tiendavirtualsqlite.classes.Product
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

        val jsonArray = JSONArray("[{\"idProducto\":1,\"descripcion\":\"Delicioso plato de pasta con salsa de tomate\",\"precio\":8.99,\"cantidad\":17,\"nombre\":\"Pasta con salsa de tomate\",\"imagen\":\"https://loremflickr.com/640/480/food\"},{\"idProducto\":2,\"descripcion\":\"Hamburguesa gourmet con carne angus y queso cheddar\",\"precio\":10.5,\"cantidad\":24,\"nombre\":\"Hamburguesa gourmet\",\"imagen\":\"https://loremflickr.com/640/480/food\"},{\"idProducto\":3,\"descripcion\":\"Ensalada fresca con lechuga, tomate, pepino y aderezo de vinagreta\",\"precio\":6.75,\"cantidad\":15,\"nombre\":\"Ensalada mixta\",\"imagen\":\"https://loremflickr.com/640/480/food\"},{\"idProducto\":4,\"descripcion\":\"Pizza recién horneada con pepperoni, champiñones y queso mozzarella\",\"precio\":12.99,\"cantidad\":24,\"nombre\":\"Pizza pepperoni\",\"imagen\":\"https://loremflickr.com/640/480/food\"},{\"idProducto\":5,\"descripcion\":\"Sushi fresco con variedad de pescados y arroz al estilo japonés\",\"precio\":14.5,\"cantidad\":25,\"nombre\":\"Sushi variado\",\"imagen\":\"https://loremflickr.com/640/480/food\"},{\"idProducto\":6,\"descripcion\":\"Taco mexicano con carne asada, guacamole y salsa picante\",\"precio\":7.25,\"cantidad\":27,\"nombre\":\"Taco de carne asada\",\"imagen\":\"https://loremflickr.com/640/480/food\"},{\"idProducto\":7,\"descripcion\":\"Sopa de pollo casera con fideos y vegetales frescos\",\"precio\":5.99,\"cantidad\":22,\"nombre\":\"Sopa de pollo\",\"imagen\":\"https://loremflickr.com/640/480/food\"},{\"idProducto\":8,\"descripcion\":\"Filete de salmón al horno con espárragos y patatas asadas\",\"precio\":16.75,\"cantidad\":23,\"nombre\":\"Salmón al horno\",\"imagen\":\"https://loremflickr.com/640/480/food\"},{\"idProducto\":9,\"descripcion\":\"Burrito mexicano con carne de res, frijoles, arroz y queso\",\"precio\":8.5,\"cantidad\":21,\"nombre\":\"Burrito de carne\",\"imagen\":\"https://loremflickr.com/640/480/food\"},{\"idProducto\":10,\"descripcion\":\"Ceviche peruano con pescado fresco, limón y ají\",\"precio\":11.25,\"cantidad\":19,\"nombre\":\"Ceviche peruano\",\"imagen\":\"https://loremflickr.com/640/480/food\"},{\"idProducto\":11,\"descripcion\":\"Pastel de chocolate con crema batida y fresas frescas\",\"precio\":6.99,\"cantidad\":25,\"nombre\":\"Pastel de chocolate\",\"imagen\":\"https://loremflickr.com/640/480/food\"},{\"idProducto\":12,\"descripcion\":\"Tarta de manzana casera con helado de vainilla\",\"precio\":5.5,\"cantidad\":29,\"nombre\":\"Tarta de manzana\",\"imagen\":\"https://loremflickr.com/640/480/food\"},{\"idProducto\":13,\"descripcion\":\"Pollo a la parrilla con papas fritas y ensalada\",\"precio\":9.75,\"cantidad\":16,\"nombre\":\"Pollo a la parrilla\",\"imagen\":\"https://loremflickr.com/640/480/food\"},{\"idProducto\":14,\"descripcion\":\"Sándwich de pavo con lechuga, tomate y mayonesa\",\"precio\":7.25,\"cantidad\":28,\"nombre\":\"Sándwich de pavo\",\"imagen\":\"https://loremflickr.com/640/480/food\"},{\"idProducto\":15,\"descripcion\":\"Ramen japonés con huevo, cebolleta y cerdo a la parrilla\",\"precio\":10.99,\"cantidad\":25,\"nombre\":\"Ramen de cerdo\",\"imagen\":\"https://loremflickr.com/640/480/food\"}]\n")
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val producto = Product(
                descripcion = jsonObject.getString("descripcion"),
                precio = jsonObject.getDouble("precio"),
                cantidad = jsonObject.getInt("cantidad"),
                nombre = jsonObject.getString("nombre"),
                imagen = jsonObject.getString("imagen"),
                idproducto = jsonObject.getLong("idProducto")
            )
            productos.add(producto)
        }
        return  productos
    }
}