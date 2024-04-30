package com.example.tiendavirtualsqlite.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendavirtualsqlite.R
import com.example.tiendavirtualsqlite.classes.ShoppingCart
import com.example.tiendavirtualsqlite.classes.ShoppingDetail
import com.example.tiendavirtualsqlite.model.DetalleCompra
import com.example.tiendavirtualsqlite.model.Producto

class MyShoppingsAdapter (private var shoppingsList: List<ShoppingCart>,
                          private val shoppingDetailManager: DetalleCompra,
                          private val productManager: Producto,
                          private val context: Context): RecyclerView.Adapter<MyShoppingsAdapter.MyShoppingsViewHolder>(){

    class MyShoppingsViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val tvFecha: TextView = view.findViewById(R.id.tvFecha)
        val tvProducto: TextView = view.findViewById(R.id.tvProducto)
        val tvCantidad: TextView = view.findViewById(R.id.tvCantidad)
        val tvSubtotal: TextView = view.findViewById(R.id.tvSubtotal)
        val tvTotal: TextView = view.findViewById(R.id.tvTotal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyShoppingsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return MyShoppingsViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyShoppingsViewHolder, position: Int) {
        val shop = shoppingsList[position]
        val details = shoppingDetailManager.verCompra(shop.idcompra)
        val stringProductos = StringBuilder()
        val stringCantidad = StringBuilder()
        val stringSubtotal = StringBuilder()
        var total = 0.0;

        details?.forEach {detail ->
            val product = productManager.searchProducto(detail.idProducto)
            if (product != null) {
                val subtotal = product.precio * detail.cantidad
                total+=subtotal
                stringProductos.append("${product.nombre}\n")
                stringSubtotal.append("$${subtotal}\n")
            }
            stringCantidad.append("${detail.cantidad}\n")
        }
        holder.tvFecha.text = shop.fecha
        holder.tvProducto.text = stringProductos
        holder.tvCantidad.text = stringCantidad
        holder.tvSubtotal.text = stringSubtotal
        holder.tvTotal.text = "$${total}"
    }

    override fun getItemCount() = shoppingsList.size

    fun refreshData (newShoppings: List<ShoppingCart>) {
        shoppingsList = newShoppings
        notifyDataSetChanged()
    }

}