package com.example.registrodeinventario.Presentador

import com.example.registrodeinventario.Modelo.CategoriaModel
import com.example.registrodeinventario.Modelo.MarcaModel
import com.example.registrodeinventario.Vista.Contracs.MarcaContract

class MarcaPresenter(private val vista: MarcaContract) {
    private val model = MarcaModel()

    fun guardarMarca(nombre: String) {

        if (nombre.isEmpty()) {
            vista.mostrarMensaje("Debe escribir un nombre")
            return
        }

        model.guardarMarca(nombre) { respuesta, error ->
            if (error != null) {
                vista.mostrarMensaje(error)
                return@guardarMarca
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