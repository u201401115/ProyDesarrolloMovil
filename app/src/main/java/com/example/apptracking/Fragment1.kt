package com.example.apptracking

import OrdersAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Fragment1 : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dbHelper: DbHelper
    private lateinit var adapter: OrdersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout para este fragmento
        val view = inflater.inflate(R.layout.fragment_1, container, false)

        // Inicializar RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Inicializar DbHelper
        dbHelper = DbHelper(requireContext())

        // Cargar datos en el RecyclerView
        loadOrders()

        return view
    }

    private fun loadOrders() {
        CoroutineScope(Dispatchers.IO).launch {
            val orders = dbHelper.getOrdersByStatus("En Progreso")

            withContext(Dispatchers.Main) {
                if (orders.isNotEmpty()) {
                    adapter = OrdersAdapter(orders)
                    recyclerView.adapter = adapter
                } else {

                }
            }
        }
    }
}