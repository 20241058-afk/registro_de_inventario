package com.example.registrodeinventario.Modelo

import android.util.Log
import com.example.registrodeinventario.Vista.clsDatosRespuesta
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

    // ---------------------------------------------------------
    // OBTENER ROLES
    // ---------------------------------------------------------
    fun obtenerRoles(callback: (List<String>?, String?) -> Unit) {
        api.obtenerRoles().enqueue(object : Callback<List<Rol>> {

            override fun onResponse(call: Call<List<Rol>>, response: Response<List<Rol>>) {

                Log.d("API", "Roles body: ${response.body()}")

                if (response.isSuccessful) {
                    callback(response.body()?.map { it.nombre }, null)
                } else {
                    callback(null, "Error al cargar roles")
                }
            }

            override fun onFailure(call: Call<List<Rol>>, t: Throwable) {
                callback(null, t.message)
            }
        })
    }

    // ---------------------------------------------------------
    // ENVIAR CÓDIGO
    // ---------------------------------------------------------
    fun enviarCodigo(correo: String, callback: (Boolean, String) -> Unit) {

        api.enviarCodigo(correo).enqueue(object : Callback<clsDatosRespuesta> {

            override fun onResponse(call: Call<clsDatosRespuesta>, response: Response<clsDatosRespuesta>) {

                val body = response.body()

                if (response.isSuccessful && body != null) {
                    callback(body.Estado == "true", body.Salida)
                } else {
                    callback(false, "Error en servidor")
                }
            }

            override fun onFailure(call: Call<clsDatosRespuesta>, t: Throwable) {
                callback(false, t.message ?: "Error de red")
            }
        })
    }

    // ---------------------------------------------------------
    // VERIFICAR Y GUARDAR USUARIO
    // ---------------------------------------------------------
    fun verificarYGuardar(
        nombre: String, apPaterno: String, apMaterno: String,
        correo: String, matricula: String, password: String,
        rol: String, codigo: String, callback: (Boolean, String?) -> Unit
    ) {

        Log.d("DEBUG", "ROL ENVIADO: $rol")
        Log.d("DEBUG", "CODIGO ENVIADO: $codigo")

        api.registrarUsuario(
            nombre, apPaterno, apMaterno, correo,
            matricula, password, rol, codigo
        ).enqueue(object : Callback<clsDatosRespuesta> {

            override fun onResponse(call: Call<clsDatosRespuesta>, response: Response<clsDatosRespuesta>) {

                val body = response.body()

                if (response.isSuccessful && body != null) {
                    callback(body.Estado == "true", body.Salida)
                } else {
                    callback(false, "Error en servidor")
                }
            }

            override fun onFailure(call: Call<clsDatosRespuesta>, t: Throwable) {
                callback(false, t.message ?: "Error de red")
            }
        })
    }
}
