package com.example.apptracking

import OrdersAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*

class PrincipalActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var tvVehicleInfo: TextView
    private lateinit var tvVehicleDate: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var dbHelper: DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_principal)

        // Configurar edge-to-edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btn: Button = findViewById(R.id.btnOrdenes)

        btn.setOnClickListener {
            val intent = Intent(this, OrdenesActivity::class.java)
            startActivity(intent)
        }

        // Inicializar vistas
        progressBar = findViewById(R.id.progressBar)
        tvVehicleInfo = findViewById(R.id.tvVehicleInfo)
        tvVehicleDate = findViewById(R.id.tvVehicleDate)
        recyclerView = findViewById(R.id.recyclerView)

        // Configurar RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Inicializar base de datos
        dbHelper = DbHelper(this)

        // Cargar datos
        loadData()
    }

    private fun loadData() {
        val conductorId = 1 // Este ID puede ser dinámico si usas autenticación

        progressBar.visibility = ProgressBar.VISIBLE

        lifecycleScope.launch(Dispatchers.IO) {
            val orderDAO = OrderDAO(this@PrincipalActivity)

            // Obtener datos del vehículo y las órdenes
            val vehicleData = orderDAO.getVehicleInfo(conductorId)
            val ordersData = orderDAO.getAllOrdersForConductor(conductorId)

            withContext(Dispatchers.Main) {
                progressBar.visibility = ProgressBar.GONE

                // Mostrar información del vehículo
                if (vehicleData.isNotEmpty()) {
                    tvVehicleInfo.text = """
                    Placa: ${vehicleData["placa"] ?: "N/A"}
                    Modelo: ${vehicleData["modelo"] ?: "N/A"}
                """.trimIndent()
                    tvVehicleDate.text = vehicleData["fecha_asignacion"] ?: "N/A"
                } else {
                    tvVehicleInfo.text = "No hay información del vehículo."
                    tvVehicleDate.text = ""
                }

                // Mostrar órdenes
                if (ordersData.isNotEmpty()) {
                    recyclerView.visibility = RecyclerView.VISIBLE
                    val adapter = OrdersAdapter(ordersData)
                    recyclerView.adapter = adapter
                } else {
                    recyclerView.visibility = RecyclerView.GONE
                }
            }
        }
    }
}