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
            override fun onResponse(
                call: Call<List<clsDatosRespuesta>>,
                response: Response<List<clsDatosRespuesta>>
            ) {
                if (response.isSuccessful) {
                    val listaRespuesta = response.body()

                    if (!listaRespuesta.isNullOrEmpty()) {
                        callback(listaRespuesta.first(), null)
                    } else {
                        callback(null, "Respuesta exitosa, pero cuerpo JSON vacío.")
                    }
                } else {
                    callback(null, "Error del servidor: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(
                call: Call<List<clsDatosRespuesta>>,
                t: Throwable
            ) {
                callback(null, "Error de conexión: ${t.message}")
            }
        })
    }
}
