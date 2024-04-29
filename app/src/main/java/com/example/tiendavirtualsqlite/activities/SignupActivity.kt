package com.example.tiendavirtualsqlite.activities
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tiendavirtualsqlite.databinding.ActivitySignupBinding
import com.example.tiendavirtualsqlite.model.Usuario

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private var userId: Long? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val signupBtn: Button = binding.signupBtn
        val changeLogin: TextView = binding.changeLogin

        signupBtn.setOnClickListener{
            val userManager = Usuario(this)
            val username = binding.inputUser.text.toString()
            val password = binding.inputPassword.text.toString()

            userId = userManager.signup(username, password)

            if (userId != -1L) {
                val intent = Intent(this, ProductActivity::class.java)
                intent.putExtra("userId",userId)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Error, no se pudo crear el usuario", Toast.LENGTH_LONG).show()
            }
        }

        changeLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}