package com.example.apptracking

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_inf, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_home ->{
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.menu_actividades -> {
                val intent = Intent(this, Orden::class.java)
                startActivity(intent)
                return true
            }
            R.id.menu_mapas -> {
                val intent = Intent(this, OrdenActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.menu_ruta -> {
                val intent = Intent(this, RutaActivity::class.java)
                return true
            }
            R.id.menu_cerrar ->{
                val intent = Intent(this, RutaLog::class.java)
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }
}