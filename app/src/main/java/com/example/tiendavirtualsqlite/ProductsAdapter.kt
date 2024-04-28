package com.example.tiendavirtualsqlite

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ProductsAdapter(private val productsList: List<Product>, private val context: Context) : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Initialize your views here
        val tvDescripcion: TextView = view.findViewById(R.id.tvDescripcion)
        val tvPrecio: TextView = view.findViewById(R.id.tvPrecio)
        val tvCantidad: TextView = view.findViewById(R.id.tvCantidad)
        val tvNombre: TextView = view.findViewById(R.id.tvNombre)
        val ivImagen: ImageView = view.findViewById(R.id.ivImagen)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.producto, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        // Assign product values to views here
        val producto = productsList[position]
        holder.tvDescripcion.text = producto.descripcion
        holder.tvPrecio.text = producto.precio.toString()
        holder.tvCantidad.text = producto.cantidad.toString()
        holder.tvNombre.text = producto.nombre.toString()
        Glide.with(context)
            .load(producto.imagen) // Make sure your Product class has a urlImagen property
            .into(holder.ivImagen)
    }

    override fun getItemCount() = productsList.size
}
