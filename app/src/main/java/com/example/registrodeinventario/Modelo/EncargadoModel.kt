package com.example.registrodeinventario.Modelo

import com.example.registrodeinventario.Vista.clsDatosRespuesta
import com.google.gson.GsonBuilder
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class EncargadoModel {

    private val api: ifaceApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://inventariocomputo.grupoctic.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        api = retrofit.create(ifaceApiService::class.java)
    }

    fun obtenerRoles(callback: (List<String>?, String?) -> Unit) {
        api.obtenerRoles().enqueue(object : Callback<List<Rol>> {
            override fun onResponse(call: Call<List<Rol>>, response: Response<List<Rol>>) {
                if (response.isSuccessful) {
                    val lista = response.body()?.map { it.nombre }
                    callback(lista, null)
                } else {
                    callback(null, "Error al cargar roles")
                }
            }
            override fun onFailure(call: Call<List<Rol>>, t: Throwable) {
                callback(null, t.message)
            }
        })

    }

    fun guardarEncargado(
        nombre: String,
        apPaterno: String,
        apMaterno: String,
        correo: String,
        matricula: String,
        password: String,
        codigo: String,
        rol: String,
        callback: (clsDatosRespuesta?, String?) -> Unit
    ) {
        api.guardarEncargado(
            "guardar_encargado",
            nombre, apPaterno, apMaterno,
            correo, matricula, password,
            codigo, rol
        ).enqueue(object : Callback<List<clsDatosRespuesta>> {

            override fun onResponse(
                call: Call<List<clsDatosRespuesta>>,
                response: Response<List<clsDatosRespuesta>>
            ) {

                if (response.isSuccessful && !response.body().isNullOrEmpty())
                    callback(response.body()!!.first(), null)
                else
                    callback(null, "Error en servidor")
            }

            override fun onFailure(call: Call<List<clsDatosRespuesta>>, t: Throwable) {
                callback(null, t.message)
            }
        })
    }
}
