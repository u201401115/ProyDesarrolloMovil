package com.example.apptracking

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(myContext: Context) : SQLiteOpenHelper(myContext, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "klo.db"
        private const val DATABASE_VERSION = 3
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Crear tabla de usuarios
        val sqlUsuario = """
            CREATE TABLE IF NOT EXISTS usuario (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                usu TEXT NOT NULL,
                pwd TEXT NOT NULL,
                nombre TEXT NOT NULL
            )
        """
        db.execSQL(sqlUsuario)

        // Crear tabla de unidades
        val sqlUnidades = """
            CREATE TABLE IF NOT EXISTS unidades (
                id_unidad INTEGER PRIMARY KEY AUTOINCREMENT,
                placa TEXT NOT NULL,
                marca TEXT NOT NULL,
                modelo TEXT NOT NULL,
                capacidad TEXT NOT NULL
            )
        """
        db.execSQL(sqlUnidades)

        // Crear tabla de asignaciones
        val sqlAsignaciones = """
            CREATE TABLE IF NOT EXISTS asignaciones (
                id_asignacion INTEGER PRIMARY KEY AUTOINCREMENT,
                id_conductor INTEGER NOT NULL,
                id_unidad INTEGER NOT NULL,
                fecha_asignacion DATETIME NOT NULL,
                FOREIGN KEY (id_unidad) REFERENCES unidades(id_unidad)
            )
        """
        db.execSQL(sqlAsignaciones)

        // Crear tabla de órdenes
        val sqlOrdenes = """
            CREATE TABLE IF NOT EXISTS ordenes (
                id_orden TEXT PRIMARY KEY,
                fecha_creacion DATETIME NOT NULL,
                estado_orden TEXT NOT NULL,
                id_unidad INTEGER NOT NULL,
                FOREIGN KEY (id_unidad) REFERENCES unidades(id_unidad)
            )
        """
        db.execSQL(sqlOrdenes)

        // Crear tabla de entregas
        val sqlEntregas = """
            CREATE TABLE IF NOT EXISTS entregas (
                id_entrega TEXT PRIMARY KEY,
                orden_id TEXT NOT NULL,
                producto TEXT,
                producto_id TEXT,
                destino TEXT NOT NULL,
                consignatario TEXT NOT NULL,
                estado TEXT NOT NULL,
                FOREIGN KEY (orden_id) REFERENCES ordenes(id_orden)
            )
        """
        db.execSQL(sqlEntregas)

        insertarDatosPrueba(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS usuario")
        db.execSQL("DROP TABLE IF EXISTS unidades")
        db.execSQL("DROP TABLE IF EXISTS asignaciones")
        db.execSQL("DROP TABLE IF EXISTS ordenes")
        db.execSQL("DROP TABLE IF EXISTS entregas")
        onCreate(db)
    }

    // Método para insertar datos de prueba
    private fun insertarDatosPrueba(db: SQLiteDatabase) {
        // Insertar datos en la tabla usuario
        db.execSQL("""
            INSERT INTO usuario (usu, pwd, nombre)
            VALUES ('useradmin', '1234567', 'Diego Alonso Guevara')
        """)

        // Insertar datos en la tabla unidades
        db.execSQL("""
            INSERT INTO unidades (placa, marca, modelo, capacidad)
            VALUES ('AOC123', 'Toyota', 'Hilux', '1000kg')
        """)

        // Insertar datos en la tabla asignaciones
        db.execSQL("""
            INSERT INTO asignaciones (id_conductor, id_unidad, fecha_asignacion)
            VALUES (1, 1, '2024-11-25 10:00:00')
        """)

        // Insertar datos en la tabla órdenes
        db.execSQL("""
            INSERT INTO ordenes (id_orden, fecha_creacion, estado_orden, id_unidad)
            VALUES ('#ORD5638', '2024-11-25 12:00:00', 'En Progreso', 1)
        """)

        // Insertar datos en la tabla entregas
        db.execSQL("""
            INSERT INTO entregas (id_entrega, orden_id, producto, producto_id, destino, consignatario, estado)
            VALUES ('A67495', '#ORD5638', NULL, NULL, 'Chorrillos - Lima', 'Empresa 1', 'En Progreso')
        """)
    }

    fun getOrderById(orderId: String): Map<String, String> {
        val db = readableDatabase
        val query = """
            SELECT id_orden, fecha_creacion, estado_orden
            FROM ordenes
            WHERE id_orden = ?
        """
        val cursor = db.rawQuery(query, arrayOf(orderId))
        val result = mutableMapOf<String, String>()

        if (cursor.moveToFirst()) {
            result["id_orden"] = cursor.getString(cursor.getColumnIndexOrThrow("id_orden"))
            result["fecha_creacion"] = cursor.getString(cursor.getColumnIndexOrThrow("fecha_creacion"))
            result["estado_orden"] = cursor.getString(cursor.getColumnIndexOrThrow("estado_orden"))
        }
        cursor.close()
        return result
    }

    fun getEntregasByOrderId(orderId: String): List<Map<String, String>> {
        val db = readableDatabase
        val query = """
            SELECT id_entrega, producto, producto_id, destino, consignatario, estado
            FROM entregas
            WHERE orden_id = ?
        """
        val cursor = db.rawQuery(query, arrayOf(orderId))
        val entregas = mutableListOf<Map<String, String>>()

        while (cursor.moveToNext()) {
            val entrega = mutableMapOf<String, String>()
            entrega["id_entrega"] = cursor.getString(cursor.getColumnIndexOrThrow("id_entrega"))
            entrega["producto"] = cursor.getString(cursor.getColumnIndexOrThrow("producto")) ?: "---"
            entrega["producto_id"] = cursor.getString(cursor.getColumnIndexOrThrow("producto_id")) ?: "---"
            entrega["destino"] = cursor.getString(cursor.getColumnIndexOrThrow("destino"))
            entrega["consignatario"] = cursor.getString(cursor.getColumnIndexOrThrow("consignatario"))
            entrega["estado"] = cursor.getString(cursor.getColumnIndexOrThrow("estado"))
            entregas.add(entrega)
        }
        cursor.close()
        return entregas
    }

    fun getOrdersByStatus(status: String): List<Map<String, String>> {
        val db = readableDatabase
        val query = """
        SELECT id_orden, fecha_creacion, estado_orden
        FROM ordenes
        WHERE estado_orden = ?
        ORDER BY fecha_creacion DESC
    """
        val cursor = db.rawQuery(query, arrayOf(status))
        val orders = mutableListOf<Map<String, String>>()

        while (cursor.moveToNext()) {
            val order = mutableMapOf<String, String>()
            order["id_orden"] = cursor.getString(cursor.getColumnIndexOrThrow("id_orden"))
            order["fecha_creacion"] = cursor.getString(cursor.getColumnIndexOrThrow("fecha_creacion"))
            order["estado_orden"] = cursor.getString(cursor.getColumnIndexOrThrow("estado_orden"))
            orders.add(order)
        }
        cursor.close()
        return orders
    }

    fun getOrdenes(): MutableList<Entrega> {
        val db = readableDatabase
        val query = """
        SELECT id_orden AS ordenId,
               producto AS producto,
               producto_id AS productoId,
               destino AS destino,
               consignatario AS consignatario,
               estado_orden AS estado
        FROM ordenes
    """
        val cursor = db.rawQuery(query, null)
        val entregas = mutableListOf<Entrega>()

        while (cursor.moveToNext()) {
            val entrega = Entrega(
                ordenId = cursor.getString(cursor.getColumnIndexOrThrow("ordenId")),
                producto = cursor.getString(cursor.getColumnIndexOrThrow("producto")),
                productoId = cursor.getString(cursor.getColumnIndexOrThrow("productoId")),
                destino = cursor.getString(cursor.getColumnIndexOrThrow("destino")),
                consignatario = cursor.getString(cursor.getColumnIndexOrThrow("consignatario")),
                estado = cursor.getString(cursor.getColumnIndexOrThrow("estado"))
            )
            entregas.add(entrega)
        }

        cursor.close()
        return entregas
    }
}