package com.example.registrodeinventario.Modelo

import com.google.gson.annotations.SerializedName // Asegúrate de tener esta dependencia en tu build.gradle

data class clsRespuestaHistorial(
    val estado: String,
    val historial: List<clsHistorial>?
)