package com.example.registrodeinventario.Presentador

import com.example.registrodeinventario.Modelo.CategoriaModel
import com.example.registrodeinventario.Vista.Contracs.CategoriaContract

class CategoriaPresenter(private val vista: CategoriaContract) {

    private val model = CategoriaModel()

    fun guardarCategoria(nombre: String) {

        if (nombre.isEmpty()) {
            vista.mostrarMensaje("Debe escribir un nombre")
            return
        }

        model.guardarCategoria(nombre) { respuesta, error ->
            if (error != null) {
                vista.mostrarMensaje(error)
                return@guardarCategoria
            }

            if (respuesta != null && respuesta.Estado.lowercase() == "true") {
                vista.mostrarMensaje(respuesta.Salida)
                vista.limpiarCampos()
            } else {
                vista.mostrarMensaje(respuesta?.Salida ?: "Error desconocido")
            }
        }
    }
}
