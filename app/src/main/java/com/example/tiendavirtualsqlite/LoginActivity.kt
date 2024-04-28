package com.example.tiendavirtualsqlite
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tiendavirtualsqlite.databinding.ActivityLoginBinding
import com.example.tiendavirtualsqlite.model.Usuario

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var userId: Long? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val loginBtn: Button = binding.loginBtn
        val changeSignup: TextView = binding.changeSignup

        loginBtn.setOnClickListener{
            val userManager = Usuario(this)
            val username = binding.inputUser.text.toString()
            val password = binding.inputPassword.text.toString()

            userId = userManager.login(username, password)

            if (userId != -1L) {
                val intent = Intent(this, ProductActivity::class.java)
                intent.putExtra("userId",userId)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Error, no se encontró el usuario", Toast.LENGTH_LONG).show()
            }
        }

        changeSignup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }
}