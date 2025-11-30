package com.example.registrodeinventario.Presentador

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.registrodeinventario.Modelo.EncargadoModel
import com.example.registrodeinventario.Vista.Contracs.EncargadoContract
import org.json.JSONArray

class EncargadoPresenter(private val view: EncargadoContract) {

    private val url = "https://inventariocomputo.grupoctic.com/apiEncargado.php?action=obtener_roles"
    private val model = EncargadoModel()

    fun cargarRoles() {
        val req = StringRequest(Request.Method.GET, url, { response ->

            try {
                val jsonArray = JSONArray(response)
                val lista = ArrayList<String>()

                for (i in 0 until jsonArray.length()) {
                    val obj = jsonArray.getJSONObject(i)
                    lista.add(obj.getString("nombre"))
                }

                view.cargarRoles(lista)

            } catch (e: Exception) {
                view.mostrarMensaje("Error al procesar roles")
            }

        }, {
            view.mostrarMensaje("Error en conexión")
        })

        Volley.newRequestQueue((view as Context)).add(req)
    }

    fun enviarCodigo(correo: String) {
        model.enviarCodigo(correo) { success, mensaje ->
            view.mostrarMensaje(mensaje)
        }
    }

    fun guardarEncargadoConCodigo(
        nombre: String, apPaterno: String, apMaterno: String,
        correo: String, matricula: String, password: String,
        rol: String, codigo: String
    ) {
        model.verificarYGuardar(
            nombre, apPaterno, apMaterno, correo,
            matricula, password, rol, codigo
        ) { success, mensaje ->
            view.mostrarMensaje(mensaje ?: "")

            if (success) view.limpiarCampos()
        }
    }
}
