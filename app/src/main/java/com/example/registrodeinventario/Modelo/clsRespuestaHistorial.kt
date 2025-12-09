package com.example.registrodeinventario.Modelo

import com.google.gson.annotations.SerializedName // Asegúrate de tener esta dependencia en tu build.gradle

data class clsRespuestaHistorial(
    // Mapea el campo JSON "Estado" a la variable 'estado'
    @SerializedName("Estado")
    val estado: String,

    // Mapea el campo JSON "Historial" a la variable 'historial'
    @SerializedName("Historial")
    val historial: List<clsHistorial>?
)