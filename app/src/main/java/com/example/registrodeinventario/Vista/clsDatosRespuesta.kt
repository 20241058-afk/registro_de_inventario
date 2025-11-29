package com.example.registrodeinventario.Vista

class clsDatosRespuesta(
    val Estado: String,
    val Salida: String,
    val user_id: Int? = null,
    val rol: Int? = null   // <-- AGREGAR ESTO
)
