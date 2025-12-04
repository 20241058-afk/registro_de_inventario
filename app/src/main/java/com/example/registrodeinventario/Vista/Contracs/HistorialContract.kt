package com.example.registrodeinventario.Vista.Contracs

import com.example.registrodeinventario.Modelo.clsHistorial

interface HistorialContract {
    fun mostrarHistorial(lista: List<clsHistorial>)
    fun mostrarError(mensaje: String)

}