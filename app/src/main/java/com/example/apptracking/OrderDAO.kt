package com.example.apptracking

import android.content.Context

class OrderDAO(context: Context) {
    private val dbHelper = DbHelper(context)

    // Obtener información del vehículo asignado al conductor
    fun getVehicleInfo(conductorId: Int): Map<String, String> {
        val db = dbHelper.readableDatabase
        val query = """
            SELECT U.placa, U.marca, U.modelo, A.fecha_asignacion
            FROM unidades U
            JOIN asignaciones A ON U.id_unidad = A.id_unidad
            WHERE A.id_conductor = ?
            ORDER BY A.fecha_asignacion DESC
            LIMIT 1
        """
        val cursor = db.rawQuery(query, arrayOf(conductorId.toString()))
        val result = mutableMapOf<String, String>()

        if (cursor.moveToFirst()) {
            result["placa"] = cursor.getString(cursor.getColumnIndexOrThrow("placa"))
            result["marca"] = cursor.getString(cursor.getColumnIndexOrThrow("marca"))
            result["modelo"] = cursor.getString(cursor.getColumnIndexOrThrow("modelo"))
            result["fecha_asignacion"] = cursor.getString(cursor.getColumnIndexOrThrow("fecha_asignacion"))
        }
        cursor.close()
        return result
    }

    // Obtener todas las órdenes relacionadas con el conductor
    fun getAllOrdersForConductor(conductorId: Int): List<Map<String, String>> {
        val db = dbHelper.readableDatabase
        val query = """
            SELECT O.fecha_creacion AS fecha_hora, O.id_orden, O.estado_orden AS estado
            FROM ordenes O
            JOIN unidades U ON O.id_unidad = U.id_unidad
            JOIN asignaciones A ON U.id_unidad = A.id_unidad
            WHERE A.id_conductor = ?
            ORDER BY O.fecha_creacion DESC
        """
        val cursor = db.rawQuery(query, arrayOf(conductorId.toString()))
        val orders = mutableListOf<Map<String, String>>()

        while (cursor.moveToNext()) {
            val order = mutableMapOf<String, String>()
            order["fecha_hora"] = cursor.getString(cursor.getColumnIndexOrThrow("fecha_hora"))
            order["id_orden"] = cursor.getString(cursor.getColumnIndexOrThrow("id_orden"))
            order["estado"] = cursor.getString(cursor.getColumnIndexOrThrow("estado"))
            orders.add(order)
        }
        cursor.close()
        return orders
    }
}