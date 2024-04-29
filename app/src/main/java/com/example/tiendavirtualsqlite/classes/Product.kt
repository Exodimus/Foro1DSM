package com.example.tiendavirtualsqlite.classes

data class Product(
    val descripcion: String,
    val precio: Double,
    val cantidad: Int,
    val nombre: String,
    val imagen: String,
    val idproducto: Long)
