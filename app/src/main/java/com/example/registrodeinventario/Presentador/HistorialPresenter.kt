package com.example.registrodeinventario.Presentador

import com.example.registrodeinventario.Modelo.clsHistorial
import com.example.registrodeinventario.Modelo.clsRespuestaHistorial
import com.example.registrodeinventario.Modelo.ifaceApiService
import com.example.registrodeinventario.Vista.Contracs.HistorialContract
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class HistorialPresenter(private val view: HistorialContract) {

    private val api: ifaceApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://inventariocomputo.grupoctic.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(ifaceApiService::class.java)
    }

    fun obtenerHistorial(idUsuario: Int) {
        // Usa clsRespuestaHistorial en el Callback
        api.obtenerHistorial(idUsuario).enqueue(object : Callback<clsRespuestaHistorial> {

            override fun onResponse(
                call: Call<clsRespuestaHistorial>, // Usar clsRespuestaHistorial
                response: Response<clsRespuestaHistorial> // Usar clsRespuestaHistorial
            ) {
                if (response.isSuccessful) {
                    val respuesta = response.body()

                    // Asegurarse de que el estado sea 'true' y la lista no sea nula/vacía
                    if (respuesta?.estado == "true" && !respuesta.historial.isNullOrEmpty()) {
                        view.mostrarHistorial(respuesta.historial)
                    } else {
                        view.mostrarError("No hay historial disponible o el servidor reportó un error.")
                    }
                } else {
                    view.mostrarError("Error al conectar: Código ${response.code()}")
                }
            }

            override fun onFailure(call: Call<clsRespuestaHistorial>, t: Throwable) {
                view.mostrarError("Fallo de red: ${t.message}")
            }
        })
    }
}
