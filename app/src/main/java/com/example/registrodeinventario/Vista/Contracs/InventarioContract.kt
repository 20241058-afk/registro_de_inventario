package com.example.registrodeinventario.Vista.Contracs

import com.example.registrodeinventario.Modelo.ItemSpinner

interface InventarioContract {
    fun mostrarMensaje(mensaje: String)
    fun limpiarCampos()
    fun cargarColor(lista: List<ItemSpinner>)
    fun cargarMarca(lista: List<ItemSpinner>)
    fun cargarCategoria(lista: List<ItemSpinner>)


}