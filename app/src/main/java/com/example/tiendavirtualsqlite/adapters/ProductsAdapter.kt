package com.example.tiendavirtualsqlite.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tiendavirtualsqlite.R
import com.example.tiendavirtualsqlite.classes.Product
import com.example.tiendavirtualsqlite.model.DetalleCompra

class ProductsAdapter(private var productsList: List<Product>,
                      private val shoppingDetailManager: DetalleCompra,
                      private val shoppingCartId: Long,
                      private val context: Context) : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Initialize your views here
        val tvDescripcion: TextView = view.findViewById(R.id.tvDescripcion)
        val tvPrecio: TextView = view.findViewById(R.id.tvPrecio)
        val tvNombre: TextView = view.findViewById(R.id.tvNombre)
        val ivImagen: ImageView = view.findViewById(R.id.ivImagen)
        val quantity: EditText = view.findViewById(R.id.quantity)
        val btnAddProduct: Button = view.findViewById(R.id.btnAddProduct)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        // Assign product values to views here
        val producto = productsList[position]
        Glide.with(context)
            .load(producto.imagen) // Make sure your Product class has a urlImagen property
            .into(holder.ivImagen)
        holder.tvNombre.text = "Nombre: ${producto.nombre}"
        holder.tvDescripcion.text = "Descripción: ${producto.descripcion}"
        holder.tvPrecio.text = "Precio: $${producto.precio}"

        holder.btnAddProduct.setOnClickListener {
            val cantidadText = holder.quantity.text.toString()
            val cantidad = if (cantidadText.isNotEmpty()) cantidadText.toInt() else 0
            val details = shoppingDetailManager.verCompra(shoppingCartId)!!.find { it.idProducto == producto.idproducto }
            if(cantidad > 0) {
                if(details != null) {
                    val nuevaCant = cantidad + details.cantidad
                    shoppingDetailManager.cambiarCantidad(details.idDetalle, nuevaCant)
                    Toast.makeText(context, "Producto agregado al carrito", Toast.LENGTH_LONG).show()
                } else {
                    shoppingDetailManager.agregarProducto(shoppingCartId, producto.idproducto, cantidad)
                    Toast.makeText(context, "Producto agregado al carrito", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun getItemCount() = productsList.size

    fun refreshData(newProducts: List<Product>) {
        productsList = newProducts
        notifyDataSetChanged()
    }
}
