package com.example.apptracking

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RutaLogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ruta_log)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Lista de ejemplo de transacciones
        val transactionList = listOf(
            RutaLog("Tol Dalam Kota", "B 2971 STJ", "10 Apr 2021 10:23", "Rp 12.000"),
            RutaLog("Tol Jagorawi", "B 2971 STJ", "06 Apr 2021 19:23", "Rp 12.000"),
            RutaLog("Tol Cikampek", "B 2971 STJ", "05 Apr 2021 09:23", "Rp 12.000"),
            RutaLog("Tol Tangerang", "B 2971 STJ", "02 Apr 2021 10:23", "Rp 8.000"),
            RutaLog("Tol Dalam Kota", "B 2971 STJ", "02 Apr 2021 19:23", "Rp 12.000")
        )

        // Configuración del RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewTransactions)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RutaLogAdapter(transactionList)
    }
}