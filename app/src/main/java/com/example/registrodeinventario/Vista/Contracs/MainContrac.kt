package com.example.registrodeinventario.Vista.Contracs

import com.example.registrodeinventario.Modelo.clsEquipos

interface MainContrac {
    fun mostrarEquipos(equipos: List<clsEquipos>)
    fun mostrarError(mensaje: String)
    fun mostrarvideo(video:String)
}