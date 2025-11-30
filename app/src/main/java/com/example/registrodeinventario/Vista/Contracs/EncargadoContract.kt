package com.example.registrodeinventario.Vista.Contracs

interface EncargadoContract {
    fun mostrarMensaje(mensaje: String)
    fun limpiarCampos()
    fun cargarRoles(lista: List<String>)
}