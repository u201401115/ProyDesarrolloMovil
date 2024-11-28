package com.example.apptracking

data class Entrega(
    val ordenId: String,
    val producto: String,
    val productoId: String,
    val destino: String,
    val consignatario: String,
    val estado: String
)