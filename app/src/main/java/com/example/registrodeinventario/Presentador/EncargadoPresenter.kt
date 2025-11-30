package com.example.registrodeinventario.Presentador

import com.example.registrodeinventario.Modelo.EncargadoModel
import com.example.registrodeinventario.Vista.Contracs.EncargadoContract

class EncargadoPresenter(private val view: EncargadoContract) {

    private val model = EncargadoModel()

    fun cargarRoles() {
        // Usando el método de Retrofit del Model
        model.obtenerRoles { lista, error ->
            if (lista != null) {
                view.cargarRoles(lista)
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
        model.verificarYGuardar(
            nombre, apPaterno, apMaterno, correo,
            usuario, password, rol, codigo
        ) { success, mensaje ->
            view.mostrarMensaje(mensaje ?: "")

            if (success) view.limpiarCampos()
        }
    }
}