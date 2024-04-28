package com.example.tiendavirtualsqlite
import org.json.JSONArray
import java.io.File

data class Product(
    val descripcion: String,
    val precio: String,
    val cantidad: Int,
    val nombre: String,
    val imagen: String,
    val idproducto: String)
