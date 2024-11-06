package com.example.apptracking

import android.os.Bundle
import android.view.Gravity
import android.widget.FrameLayout

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView

class DetalleActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configurar el RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewPedidos)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Crear una lista de ejemplo de pedidos
        val pedidos = listOf(
            Pedido("Pedido 0038", "Pendiente"),
            Pedido("Pedido 0039", "Pendiente"),
            Pedido("Pedido 0040", "Pendiente"),
            Pedido("Pedido 0041", "Pendiente"),
            Pedido("Pedido 0042", "Pendiente"),
            Pedido("Pedido 0043", "Cargado"),
            Pedido("Pedido 0044", "En Proceso")
        )

        // Configurar el adaptador con la lista de pedidos
        recyclerView.adapter = PedidosAdapter(pedidos)
    }
}