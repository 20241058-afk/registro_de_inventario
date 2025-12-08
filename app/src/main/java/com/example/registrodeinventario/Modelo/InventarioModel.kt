package com.example.registrodeinventario.Modelo

import android.content.Context
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InventarioModel {

    private val api: ifaceApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://inventariocomputo.grupoctic.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(ifaceApiService::class.java)
    }

    fun registrarInventario(
        context: Context,
        numInv: String,
        serie: String,
        categoria: String,
        marca: String,
        color: String,
        callback: (String, String) -> Unit
    ) {
        val queue = Volley.newRequestQueue(context)
        val url = "https://inventariocomputo.grupoctic.com/apiInventario.php?action=guardar_inventario"

        val postRequest = object : StringRequest(
            Method.POST,
            url,
            { response ->

                try {
                    val json = JSONObject(response)
                    val mensaje = json.getString("Salida")
                    callback("OK", mensaje)
                } catch (e: Exception) {
                    callback("ERROR", "Error procesando respuesta")
                }

            },
            { error ->
                callback("ERROR", "Error: ${error.message}")
            }
        ) {

            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["numInv"] = numInv
                params["serie"] = serie
                params["categoria"] = categoria
                params["marca"] = marca
                params["color"] = color
                params["accion"] = "guardar_inventario"
                return params
            }

            override fun getBodyContentType(): String {
                return "application/x-www-form-urlencoded; charset=UTF-8"
            }
        }

        queue.add(postRequest)
    }

    fun obtenerColor(callback: (List<Color>?, String?) -> Unit) {
        api.obtenerColor().enqueue(object : Callback<List<Color>> {

            override fun onResponse(call: Call<List<Color>?>,response: Response<List<Color>?>
            ) {
                // Ahora response.body() (List<Color>) coincide con el tipo que espera el callback.
                if (response.isSuccessful) callback(response.body(), null)
                else callback(null, "Error al cargar colores")
            }

            override fun onFailure(
                call: Call<List<Color>?>,
                t: Throwable
            ) {
                callback(null, t.message)
            }
        })
    }

    // 2. CORRECCIÓN para Marca: Cambia List<ItemSpinner> por List<Marca>
    fun obtenerMarca(callback: (List<Marca>?, String?) -> Unit) {
        api.obtenerMarca().enqueue(object : Callback<List<Marca>> {

            override fun onResponse(call: Call<List<Marca>?>,response: Response<List<Marca>?>
            ) {
                // Ahora response.body() (List<Marca>) coincide con el tipo que espera el callback.
                if (response.isSuccessful) callback(response.body(), null)
                else callback(null, "Error al cargar marcas")
            }

            override fun onFailure(
                call: Call<List<Marca>?>,
                t: Throwable
            ) {
                callback(null, t.message)
            }
        })
    }

    // 3. CORRECCIÓN para Categoría: Cambia List<ItemSpinner> por List<Categoria>
    fun obtenerCategoria(callback: (List<Categoria>?, String?) -> Unit) {
        api.obtenerCategoria().enqueue(object : Callback<List<Categoria>> {

            override fun onResponse(call: Call<List<Categoria>?>, response: Response<List<Categoria>?>
            ) {
                // Ahora response.body() (List<Categoria>) coincide con el tipo que espera el callback.
                if (response.isSuccessful) callback(response.body(), null)
                else callback(null, "Error al cargar categorías")
            }

            override fun onFailure(
                call: Call<List<Categoria>?>,
                t: Throwable
            ) {
                callback(null, t.message)
            }
        })
    }

}
