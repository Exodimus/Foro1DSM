package com.example.tiendavirtualsqlite.classes
import org.json.JSONArray
import java.io.File

data class ShoppingCart(
    val cantidad: Int,
    val idusuario: Long,
    val idproducto: String)
