package com.example.registrodeinventario.Modelo


data class Color(val id: Int, val nombre: String){
    override fun toString(): String {
        return nombre // Muestra el nombre en el Spinner
    }
}
