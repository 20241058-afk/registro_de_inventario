package com.example.registrodeinventario.Presentador

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.registrodeinventario.Vista.Contracs.EncargadoContract
import org.json.JSONArray

class EncargadoPresenter(private val view: EncargadoContract) {

    private val url = "https://inventariocomputo.grupoctic.com/apiEncargado.php?action=obtener_roles"


    // ---------------------------------------------------------
    // CARGAR ROLES DESDE LA API
    // ---------------------------------------------------------
    fun cargarRoles() {
        val request = StringRequest(
            Request.Method.GET, url,
            { response ->

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
            },
            {
                view.mostrarMensaje("Error en conexión al cargar roles")
            }
        )

        Volley.newRequestQueue((view as Context)).add(request)
    }


    // ---------------------------------------------------------
    // GUARDAR ENCARGADO
    // ---------------------------------------------------------
    fun guardarEncargado(
        nombre: String, aPaterno: String, aMaterno: String,
        correo: String, matricula: String, pass: String,
        codigo: String, rol: String
    ) {
        // Aquí va tu código actual de guardar encargado
    }
}
