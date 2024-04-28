package com.example.tiendavirtualsqlite

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tiendavirtualsqlite.databinding.ActivityLoginBinding
import com.example.tiendavirtualsqlite.databinding.ActivityProductBinding
import com.example.tiendavirtualsqlite.model.Producto

class ProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductBinding
    private var userId: Long? = null
    private lateinit var productManager: Producto
    private lateinit var productsAdapter: ProductsAdapter
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
    }

    override fun onResume() {
        super.onResume()
        var products = productManager.searchProductosAll()
        productsAdapter.refreshData(products)
    }
}