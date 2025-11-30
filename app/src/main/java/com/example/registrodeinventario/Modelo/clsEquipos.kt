package com.example.registrodeinventario.Modelo


data class clsEquipos(
    val id_equipo: String,
    val nombre: String,
    val descripcion: String,
    val ruta_imagen: String,
    val codigo_qr: String,
    val id_categoria: Int,
    val estado: String,
    val fecha_registro: String
)

