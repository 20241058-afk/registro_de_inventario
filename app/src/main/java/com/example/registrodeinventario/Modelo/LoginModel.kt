package com.example.registrodeinventario.Modelo

import com.example.registrodeinventario.Vista.clsDatosRespuesta
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginModel {

    private val apiService: ifaceApiService

    init {
        val gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://inventariocomputo.grupoctic.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        apiService = retrofit.create(ifaceApiService::class.java)
    }

    fun iniciarSesion(
        email: String,
        password: String,
        callback: (clsDatosRespuesta?, String?) -> Unit
    ) {
        apiService.iniciarSesion(
            "login", email, password
        ).enqueue(object : Callback<List<clsDatosRespuesta>> {
            override fun onResponse(call: Call<List<clsDatosRespuesta>>, response: Response<List<clsDatosRespuesta>>) {
                // Si la respuesta del servidor es exitosa, pero el cuerpo es nulo, manejarlo.
                if (response.isSuccessful && response.body() != null) {
                    callback.invoke(response.body()!!.first(), null)
                } else {
                    callback.invoke(null, "Error en el servidor: " + response.code())
                }
            }

            override fun onFailure(call: Call<List<clsDatosRespuesta>>, t: Throwable) {
                // Si falla la red (internet, timeout, host no encontrado)
                callback.invoke(null, "Fallo de red: " + t.message)
            }
        })
    }
}
