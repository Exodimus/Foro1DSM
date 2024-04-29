package com.example.tiendavirtualsqlite.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tiendavirtualsqlite.adapters.ProductsAdapter
import com.example.tiendavirtualsqlite.R
import com.example.tiendavirtualsqlite.classes.ShoppingCart
import com.example.tiendavirtualsqlite.databinding.ActivityProductBinding
import com.example.tiendavirtualsqlite.model.Producto
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductBinding
    private var userId: Long? = -1L
    private var shoppingCartId: Long? = -1L
    private lateinit var productManager: Producto
    private lateinit var productsAdapter: ProductsAdapter
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var shoppingCart: MutableList<ShoppingCart>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        userId = intent.getLongExtra("userId", -1L)
        shoppingCart = mutableListOf()
        productManager = Producto(this)
        val products = productManager.searchProductosAll()

        setContentView(binding.root)

        productsAdapter = ProductsAdapter(products, userId!!, shoppingCart, this)

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
                    startActivity(intent)
                    true
                }
                R.id.page_3 -> {
                    val intent = Intent(this, MyShoppingsActivity::class.java)
                    intent.putExtra("userId",userId)
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