package com.example.registrodeinventario.Presentador

import com.example.registrodeinventario.Modelo.Obtenervideo
import com.example.registrodeinventario.Modelo.clsEquipos
import com.example.registrodeinventario.Modelo.ifaceApiService
import com.example.registrodeinventario.Vista.Contracs.MainContrac
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainPresenter(private val view: MainContrac) {
    private val model = Obtenervideo()
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
            override fun onResponse(
                call: Call<List<clsEquipos>>,
                response: Response<List<clsEquipos>>
            ) {
                if (response.isSuccessful) {
                    val equipos = response.body() ?: emptyList()
                    if (equipos.isEmpty()) {
                        view.mostrarError("No hay equipos registrados")
                    } else {
                        view.mostrarEquipos(equipos)
                    }
                } else {
                    view.mostrarError("Error ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<clsEquipos>>, t: Throwable) {
                view.mostrarError("Fallo: ${t.message}")
            }
        })
    }

    fun caragarVide(){
            val url = model.obtenerURL()
            view.mostrarvideo(url)

    }

}