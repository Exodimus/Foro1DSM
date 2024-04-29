package com.example.tiendavirtualsqlite.activities
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tiendavirtualsqlite.db.HelperDB

class MainActivity : AppCompatActivity() {
    private var dbHelper: HelperDB? = null
    private var db: SQLiteDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dbHelper = HelperDB(this)
        db = dbHelper!!.writableDatabase
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }


}