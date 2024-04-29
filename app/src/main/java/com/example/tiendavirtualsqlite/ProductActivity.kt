package com.example.tiendavirtualsqlite

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tiendavirtualsqlite.databinding.ActivityLoginBinding
import com.example.tiendavirtualsqlite.databinding.ActivityProductBinding
import com.example.tiendavirtualsqlite.model.Producto
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductBinding
    private var userId: Long? = null
    private lateinit var productManager: Producto
    private lateinit var productsAdapter: ProductsAdapter
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        userId = intent.getLongExtra("userId", -1L)
        setContentView(binding.root)
        productManager = Producto(this)
        var products = productManager.searchProductosAll()
        productsAdapter = ProductsAdapter(products, this)
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
        var products = productManager.searchProductosAll()
        productsAdapter.refreshData(products)
    }
}