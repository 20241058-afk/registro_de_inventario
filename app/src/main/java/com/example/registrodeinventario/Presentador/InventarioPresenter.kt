package com.example.registrodeinventario.Presentador

import android.content.Context
import com.example.registrodeinventario.Modelo.InventarioModel
import com.example.registrodeinventario.Vista.Contracs.InventarioContract

class InventarioPresenter(private val view: InventarioContract) {

    private val model = InventarioModel()

    fun guardarInventario(
        context: Context,
        numInv: String,
        serie: String,
        categoria: String,
        marca: String,
        color: String
    )
    {

        // ==== VALIDACIONES ====

        if (numInv.isBlank()) {
            view.mostrarMensaje("El número de inventario es obligatorio")
            return
        }

        if (serie.isBlank()) {
            view.mostrarMensaje("El número de serie es obligatorio")
            return
        }

        if (categoria.isBlank() || categoria == "Seleccione") {
            view.mostrarMensaje("Selecciona una categoría")
            return
        }

        if (marca.isBlank() || marca == "Seleccione") {
            view.mostrarMensaje("Selecciona una marca")
            return
        }

        if (color.isBlank() || color == "Seleccione") {
            view.mostrarMensaje("Selecciona un color")
            return
        }

        // Si todo está bien → enviar al modelo
        model.registrarInventario(context, numInv, serie, categoria, marca, color) { estado, msg ->
            view.mostrarMensaje(msg)
            if (estado == "OK") {
                view.limpiarCampos()
            }
        }
    }

    fun cargarColor() {
        model.obtenerColor { lista, err ->
            if (lista != null) view.cargarColor(lista)
            else view.mostrarMensaje(err ?: "Error al cargar color")
        }
    }

    fun cargarMarca() {
        model.obtenerMarca { lista, err ->
            if (lista != null) view.cargarMarca(lista)
            else view.mostrarMensaje(err ?: "Error al cargar marca")
        }
    }

    fun cargarCategoria() {
        model.obtenerCategoria { lista, err ->
            if (lista != null) view.cargarCategoria(lista)
            else view.mostrarMensaje(err ?: "Error al cargar categoría")
        }
    }
}
