package com.example.tiendavirtualsqlite

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tiendavirtualsqlite.databinding.ActivityLoginBinding
import com.example.tiendavirtualsqlite.databinding.ActivityProductBinding
import com.example.tiendavirtualsqlite.model.Producto

class ProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductBinding
    private var userId: Long? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        userId = intent.getLongExtra("userId", -1L)
        setContentView(binding.root)
        val recyclerView = binding.productsContainer


    }
}