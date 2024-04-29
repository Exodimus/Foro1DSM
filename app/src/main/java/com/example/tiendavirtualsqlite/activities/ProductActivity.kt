package com.example.tiendavirtualsqlite.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tiendavirtualsqlite.adapters.ProductsAdapter
import com.example.tiendavirtualsqlite.R
import com.example.tiendavirtualsqlite.databinding.ActivityProductBinding
import com.example.tiendavirtualsqlite.model.Compra
import com.example.tiendavirtualsqlite.model.DetalleCompra
import com.example.tiendavirtualsqlite.model.Producto
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductBinding
    private var userId: Long? = -1L
    private var shoppingId: Long? = -1L
    private lateinit var productManager: Producto
    private lateinit var shoppingManager: Compra
    private lateinit var shoppingDetailManager: DetalleCompra
    private lateinit var productsAdapter: ProductsAdapter
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productManager = Producto(this)
        shoppingManager = Compra(this)
        shoppingDetailManager = DetalleCompra(this)
        binding = ActivityProductBinding.inflate(layoutInflater)


        val products = productManager.searchProductosAll()
        userId = intent.getLongExtra("userId", -1L)
        shoppingId = intent.getLongExtra("shoppingId", shoppingManager.crearCompra(userId!!))
        setContentView(binding.root)

        productsAdapter = ProductsAdapter(products, shoppingDetailManager, shoppingId!!, this)

        binding.productsContainer.layoutManager = LinearLayoutManager(this)
        binding.productsContainer.adapter = productsAdapter

        bottomNavigationView = binding.bottomNavigation
        bottomNavigationView.selectedItemId = R.id.page_1

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.page_1 -> {
                    true
                }
                R.id.page_2 -> {
                    val intent = Intent(this, ShoppingCartActivity::class.java)
                    intent.putExtra("userId",userId)
                    intent.putExtra("shoppingId",shoppingId)
                    startActivity(intent)
                    true
                }
                R.id.page_3 -> {
                    val intent = Intent(this, MyShoppingsActivity::class.java)
                    intent.putExtra("userId",userId)
                    intent.putExtra("shoppingId",shoppingId)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val products = productManager.searchProductosAll()
        productsAdapter.refreshData(products)
    }
}