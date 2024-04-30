package com.example.tiendavirtualsqlite.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tiendavirtualsqlite.R
import com.example.tiendavirtualsqlite.adapters.ShoppingCartAdapter
import com.example.tiendavirtualsqlite.databinding.ActivityShoppingCartBinding
import com.example.tiendavirtualsqlite.model.Compra
import com.example.tiendavirtualsqlite.model.DetalleCompra
import com.example.tiendavirtualsqlite.model.Producto
import com.google.android.material.bottomnavigation.BottomNavigationView

class ShoppingCartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShoppingCartBinding
    private var userId: Long? = -1L
    private var shoppingId: Long? = -1L
    private lateinit var shoppingManager: Compra
    private lateinit var productManager: Producto
    private lateinit var shoppingDetailManager: DetalleCompra
    private lateinit var shoppingCartAdapter: ShoppingCartAdapter
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productManager = Producto(this)
        shoppingManager = Compra(this)
        shoppingDetailManager = DetalleCompra(this)

        binding = ActivityShoppingCartBinding.inflate(layoutInflater)
        userId = intent.getLongExtra("userId", -1L)
        shoppingId = shoppingManager.obtenerCompraActiva(userId!!)
        if(shoppingId == -1L) {
            shoppingId = shoppingManager.crearCompra(userId!!)
        }

        val details = shoppingDetailManager.verCompra(shoppingId!!)

        setContentView(binding.root)

        shoppingCartAdapter = ShoppingCartAdapter(details!!, productManager, shoppingDetailManager, this)

        bottomNavigationView = binding.bottomNavigation
        bottomNavigationView.selectedItemId = R.id.page_2

        binding.shoppingsContainer.layoutManager = LinearLayoutManager(this)
        binding.shoppingsContainer.adapter = shoppingCartAdapter

        binding.btnShop.setOnClickListener {
            shoppingManager.finalizarCompra(shoppingId!!)
            Toast.makeText(this, "Su compra ha finalizado", Toast.LENGTH_LONG).show()
            val intent = Intent(this, ProductActivity::class.java)
            intent.putExtra("userId",userId)
            startActivity(intent)
        }

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.page_1 -> {
                    val intent = Intent(this, ProductActivity::class.java)
                    intent.putExtra("userId",userId)
                    startActivity(intent)
                    true
                }
                R.id.page_2 -> {
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
        val details = shoppingDetailManager.verCompra(shoppingId!!)
        shoppingCartAdapter.refreshData(details!!)
    }
}