package com.example.tiendavirtualsqlite.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tiendavirtualsqlite.R
import com.example.tiendavirtualsqlite.adapters.MyShoppingsAdapter
import com.example.tiendavirtualsqlite.databinding.ActivityMyShoppingsBinding
import com.example.tiendavirtualsqlite.model.Compra
import com.example.tiendavirtualsqlite.model.DetalleCompra
import com.example.tiendavirtualsqlite.model.Producto

class MyShoppingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyShoppingsBinding
    private var userId: Long? = null
    private var shoppingId: Long? = -1L
    private lateinit var shoppingManager: Compra
    private lateinit var productManager: Producto
    private lateinit var detailsManager: DetalleCompra
    private lateinit var shoppingsAdapter: MyShoppingsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        shoppingManager = Compra(this)
        productManager = Producto(this)
        detailsManager = DetalleCompra(this)
        binding = ActivityMyShoppingsBinding.inflate(layoutInflater)

        userId = intent.getLongExtra("userId", -1L)
        shoppingId = shoppingManager.obtenerCompraActiva(userId!!)
        if(shoppingId == -1L) {
            shoppingId = shoppingManager.crearCompra(userId!!)
        }

        val shoppings = shoppingManager.obtenerComprasFinalizadas(userId!!)
        setContentView(binding.root)
        shoppingsAdapter = MyShoppingsAdapter(shoppings, detailsManager, productManager, this)

        binding.shoppingsContainer.layoutManager = LinearLayoutManager(this)
        binding.shoppingsContainer.adapter = shoppingsAdapter

        val bottomNavigationView = binding.bottomNavigation
        bottomNavigationView.selectedItemId = R.id.page_3

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.page_1 -> {
                    val intent = Intent(this, ProductActivity::class.java)
                    intent.putExtra("userId", userId)
                    startActivity(intent)
                    true
                }

                R.id.page_2 -> {
                    val intent = Intent(this, ShoppingCartActivity::class.java)
                    intent.putExtra("userId", userId)
                    startActivity(intent)
                    true
                }

                R.id.page_3 -> {
                    true
                }

                else -> false
            }
        }
    }
}