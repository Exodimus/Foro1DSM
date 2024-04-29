package com.example.tiendavirtualsqlite.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.tiendavirtualsqlite.R
import com.example.tiendavirtualsqlite.databinding.ActivityMyShoppingsBinding
import com.example.tiendavirtualsqlite.model.Producto

class MyShoppingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyShoppingsBinding
    private var userId: Long? = null
    private lateinit var productManager: Producto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMyShoppingsBinding.inflate(layoutInflater)
        userId = intent.getLongExtra("userId", -1L)
        setContentView(binding.root)
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