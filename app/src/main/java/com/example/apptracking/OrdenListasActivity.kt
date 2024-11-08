package com.example.apptracking

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class OrdenListasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_orden_listas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val orderList = listOf(
            Orden("01 Nov 2024", "Según Guía", "Cliente ABC", "F01-0001"),
            Orden("02 Nov 2024", "Según Guía", "Cliente XYZ", "F01-0002"),
            Orden("03 Nov 2024", "Según Guía", "Cliente 123", "F01-0003")
        )

        // Configuración del RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewOrders)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = OrderAdapter(orderList)

        val btnContinue: Button = findViewById(R.id.btnContinue)
        btnContinue.setOnClickListener {
            // Intent para abrir la siguiente actividad
            val intent = Intent(this, OrdenDetalleActivity::class.java)
            startActivity(intent)
        }
    }
}