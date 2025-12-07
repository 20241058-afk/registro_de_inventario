package com.example.registrodeinventario.Presentador

import com.example.registrodeinventario.Modelo.EncargadoModel
import com.example.registrodeinventario.Vista.Contracs.EncargadoContract

class EncargadoPresenter(private val view: EncargadoContract) {

    private val model = EncargadoModel()

    fun cargarRoles() {
        // Usando el método de Retrofit del Model
        model.obtenerRoles { lista, error ->
            if (lista != null) {val listaConMensaje = mutableListOf("Seleccione un rol")
                listaConMensaje.addAll(lista)

                view.cargarRoles(listaConMensaje)
            } else {
                view.mostrarMensaje(error ?: "Error al cargar roles")
            }
        }
    }

    fun enviarCodigo(correo: String) {
        model.enviarCodigo(correo) { success, mensaje ->
            view.mostrarMensaje(mensaje)
        }
    }

    fun guardarEncargadoConCodigo(
        nombre: String, apPaterno: String, apMaterno: String,
        correo: String, usuario: String, password: String,
        rol: String, codigo: String
    ) {
        if (rol == "Seleccione un rol") {
            view.mostrarMensaje("Debe seleccionar un rol")
            return
        }
        model.verificarYGuardar(
            nombre, apPaterno, apMaterno, correo,
            usuario, password, rol, codigo
        ) { success, mensaje ->
            view.mostrarMensaje(mensaje ?: "")

            if (success) view.limpiarCampos()
        }
    }
}