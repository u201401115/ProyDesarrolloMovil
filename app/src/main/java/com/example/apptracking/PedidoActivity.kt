package com.example.apptracking

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PedidoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pedido)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tvCodigo: TextView = findViewById(R.id.tvCodigo)
        val tvEmpresa: TextView = findViewById(R.id.tvEmpresa)
        val tvFecha: TextView = findViewById(R.id.tvFecha)
        val tvEstado: TextView = findViewById(R.id.tvEstado)
        val btnCerrar: Button = findViewById(R.id.btnCerrar)
        val btnDescargar: Button = findViewById(R.id.btnDescargar)

        tvCodigo.text = "Código: 0044"
        tvEmpresa.text = "Empresa: KLO PERÚ SAC"
        tvFecha.text = "Fecha: 24/10/2024 - T16:00"
        tvEstado.text = "Estado: En ruta"

        btnCerrar.setOnClickListener {
            finish()
        }
    }
}