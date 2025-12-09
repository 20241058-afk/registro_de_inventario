package com.example.registrodeinventario.Vista.Contracs

interface LoginContract {
    fun mostrarMensaje(mensaje: String)
    fun navegarPorRol(userId: Int, rolId: Int)
    fun navegarAMain()
    fun navegarAAdmin()

}