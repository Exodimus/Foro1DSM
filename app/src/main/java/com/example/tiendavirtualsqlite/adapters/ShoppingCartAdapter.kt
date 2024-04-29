package com.example.tiendavirtualsqlite.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendavirtualsqlite.R
import com.example.tiendavirtualsqlite.classes.ShoppingDetail
import com.example.tiendavirtualsqlite.model.Producto

class ShoppingCartAdapter(private var details: List<ShoppingDetail>,
                          private val productManager: Producto,
                          private val context: Context) : RecyclerView.Adapter<ShoppingCartAdapter.ShoppingCartHolder>() {
    class ShoppingCartHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvNombre: TextView = view.findViewById(R.id.tvNombre)
        val tvCantidad: TextView = view.findViewById(R.id.tvCantidad)
        val tvPrecio: TextView = view.findViewById(R.id.tvPrecio)
        val tvSubtotal: TextView = view.findViewById(R.id.tvSubtotal)
        val btnDelete: Button = view.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingCartAdapter.ShoppingCartHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shoppingcart_item, parent, false)
        return ShoppingCartHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingCartAdapter.ShoppingCartHolder, position: Int) {
        val detail = details[position]
        val product = productManager.searchProducto(detail.idProducto)
        holder.tvNombre.text = product!!.nombre
        holder.tvCantidad.text = detail.cantidad.toString()
        holder.tvPrecio.text =  "$${product.precio}"
        holder.tvSubtotal.text = "$${(detail.cantidad * product.precio)}"
    }

    override fun getItemCount() = details.size

    fun refreshData(newDetails: List<ShoppingDetail>) {
        details = newDetails
        notifyDataSetChanged()
    }
}