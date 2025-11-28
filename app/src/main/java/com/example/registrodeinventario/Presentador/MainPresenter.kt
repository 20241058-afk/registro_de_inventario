package com.example.registrodeinventario.Presentador

import com.example.registrodeinventario.Modelo.clsEquipos
import com.example.registrodeinventario.Modelo.ifaceApiService
import com.example.registrodeinventario.Vista.Contracs.MainContrac
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainPresenter(private val view: MainContrac) {
    private val apiService: ifaceApiService

    init {
        val BASE_URL = "https://inventariocomputo.grupoctic.com/"

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ifaceApiService::class.java)
    }

    fun obtenerEquipos() {
        apiService.obtenerEquipos().enqueue(object : Callback<List<clsEquipos>> {
            override fun onResponse(call: Call<List<clsEquipos>>, response: Response<List<clsEquipos>>) {
                if (response.isSuccessful) {
                    response.body()?.let { peliculas ->
                        view.mostrarEquipos(peliculas)
                    } ?: run {
                        view.mostrarError("Error: La lista de películas está vacía.")
                    }
                } else {
                    val errorMessage = "Error ${response.code()}: ${response.message()}"
                    view.mostrarError(errorMessage)
                }
            }

            override fun onFailure(call: Call<List<clsEquipos>>, t: Throwable) {
                view.mostrarError("Fallo de conexión: ${t.message}")
            }
        })
    }
}