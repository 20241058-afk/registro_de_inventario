package com.example.registrodeinventario.Presentador

// ... (imports necesarios)
import com.example.registrodeinventario.Modelo.clsRespuestaHistorial
import com.example.registrodeinventario.Modelo.ifaceApiService
import com.example.registrodeinventario.Vista.Contracs.HistorialContract
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class HistorialPresenter(private val view: HistorialContract) {

    private val api: ifaceApiService
    private val scope = CoroutineScope(Dispatchers.IO) // Usar IO para red

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://inventariocomputo.grupoctic.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(ifaceApiService::class.java)
    }

    // Usar Corrutinas: el Presenter llama a la API en un hilo de fondo (Dispatchers.IO)
    fun obtenerHistorial(idUsuario: Int) {
        scope.launch {
            try {
                // 1. Llamada suspendida (bloquea la corrutina, no el hilo principal)
                val response = api.obtenerHistorial(idUsuario)

                // 2. Volver al hilo principal para actualizar la UI
                launch(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val respuesta = response.body()

                        if (respuesta?.estado == "true" && !respuesta.historial.isNullOrEmpty()) {
                            view.mostrarHistorial(respuesta.historial)
                        } else {
                            view.mostrarError("No hay historial disponible o el servidor reportó un error.")
                        }
                    } else {
                        view.mostrarError("Error al conectar: Código ${response.code()}")
                    }
                }
            } catch (e: Exception) {
                // Volver al hilo principal para mostrar error
                launch(Dispatchers.Main) {
                    view.mostrarError("Fallo de red: ${e.message}")
                }
            }
        }
    }
}