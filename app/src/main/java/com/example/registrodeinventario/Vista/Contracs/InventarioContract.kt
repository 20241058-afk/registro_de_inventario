package com.example.registrodeinventario.Vista.Contracs

import com.example.registrodeinventario.Modelo.Color
import com.example.registrodeinventario.Modelo.Marca
import com.example.registrodeinventario.Modelo.Categoria

interface InventarioContract {
    fun mostrarMensaje(mensaje: String)
    fun limpiarCampos()
    fun cargarColor(lista: List<Color>)
    fun cargarMarca(lista: List<Marca>)
    fun cargarCategoria(lista: List<Categoria>)


}