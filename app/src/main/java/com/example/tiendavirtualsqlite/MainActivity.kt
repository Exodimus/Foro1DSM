package com.example.tiendavirtualsqlite
import com.example.tiendavirtualsqlite.model.Usuario
import com.example.tiendavirtualsqlite.model.Compra
import com.example.tiendavirtualsqlite.model.Producto
import com.example.tiendavirtualsqlite.model.DetalleCompra
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var userId: Long = -1L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        declareLogin()
    }

    private fun changeView(view: Int, action: () -> Unit) {
        setContentView(view)
        action()
    }

    private fun declareLogin() {
        val loginBtn: Button = findViewById(R.id.loginBtn)
        val changeSignup: TextView = findViewById(R.id.changeSignup)

        loginBtn.setOnClickListener { login() }
        changeSignup.setOnClickListener { changeView(R.layout.signup) {
            declareSignup()
        }}
    }

    private fun declareSignup() {
        val signupBtn: Button = findViewById(R.id.signupBtn)
        val changeLogin: TextView = findViewById(R.id.changeLogin)

        signupBtn.setOnClickListener { signup() }
        changeLogin.setOnClickListener { changeView(R.layout.login) {
            declareLogin()
        }}
    }

    private fun login() {
        val userManager = Usuario(this);
        val inputUser: EditText = findViewById(R.id.inputUser)
        val inputPass: EditText = findViewById(R.id.inputPassword)
        val username:String = inputUser.text.toString()
        val password:String = inputPass.text.toString()

        userId = userManager.login(username, password)

        if (userId != -1L) {
//            changeView(R.layout.main, declareMain())
        } else {
            Toast.makeText(this, "Datos erróneneos, intente nuevamente", Toast.LENGTH_LONG).show()
        }
    }

    private fun signup() {
        val userManager = Usuario(this);
        val inputUser: EditText = findViewById(R.id.inputUser)
        val inputPass: EditText = findViewById(R.id.inputPassword)
        val username:String = inputUser.text.toString()
        val password:String = inputPass.text.toString()

        userId = userManager.signup(username, password)

        if (userId != -1L) {
//            changeView(R.layout.main, declareMain())
        } else {
            Toast.makeText(this, "Error, no se pudo crear el usuario", Toast.LENGTH_LONG).show()
        }
    }
}