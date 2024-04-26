package com.example.tiendavirtualsqlite
import com.example.tiendavirtualsqlite.model.Usuario
import com.example.tiendavirtualsqlite.model.Compra
import com.example.tiendavirtualsqlite.model.Producto
import com.example.tiendavirtualsqlite.model.DetalleCompra
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        val loginBtn: Button = findViewById(R.id.loginBtn)

        loginBtn.setOnClickListener {
            val inputUser: EditText = findViewById(R.id.inputUser)
            val inputPassword: EditText = findViewById(R.id.inputPassword)

            val username: String = inputUser.text.toString()
            val password: String = inputPassword.text.toString()


        }
    }
}