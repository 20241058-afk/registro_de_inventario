package com.example.registrodeinventario.Modelo

import com.google.gson.annotations.SerializedName

data class Marca(val id: Int, val nombre: String){ // Asumiendo estos campos
    override fun toString(): String {
        return nombre // Muestra el nombre en el Spinner
    }
}