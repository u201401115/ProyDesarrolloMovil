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

class PasivosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pasivos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Lista de ejemplo de activos
        val assetList = listOf(
            Pasivo("AD3535", "My Name", "PT. Example 1"),
            Pasivo("AD3536", "My Name", "PT. Example 1"),
            Pasivo("AD3537", "My Name", "PT. Example 1")
        )

        // Configuración del RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewAssets)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = AssetAdapter(assetList)

        val btnContinue: Button = findViewById(R.id.btnSearch)
        btnContinue.setOnClickListener {
            // Intent para abrir la siguiente actividad
            val intent = Intent(this, PasivoDetalleActivity::class.java)
            startActivity(intent)
        }
    }
}