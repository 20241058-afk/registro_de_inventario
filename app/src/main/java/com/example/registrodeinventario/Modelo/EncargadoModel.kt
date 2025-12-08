package com.example.registrodeinventario.Modelo

import android.util.Log
import com.example.registrodeinventario.Vista.clsDatosRespuesta
import com.google.gson.GsonBuilder
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class EncargadoModel {

    private val api: ifaceApiService

    init {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        // Configuración del Logger para ver las peticiones y respuestas en Logcat
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(logging) // Aquí se añade el logger
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://inventariocomputo.grupoctic.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client) // Se usa el cliente con logging
            .build()

        api = retrofit.create(ifaceApiService::class.java)
    }

    // En EncargadoModel.kt

    fun obtenerRoles(callback: (List<String>?, String?) -> Unit) {
        // Usamos api.obtenerRoles() que devuelve Call<List<String>>
        api.obtenerRoles().enqueue(object : Callback<List<String>> {
            override fun onResponse(call: Call<List<String>?>, response: Response<List<String>?>) {
                if (response.isSuccessful) {
                    callback(response.body(), null)
                } else {
                    callback(null, "Error al obtener roles")
                }
            }

            override fun onFailure(call: Call<List<String>?>, t: Throwable) {
                callback(null, t.message ?: "Error de red al cargar roles")
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
                    callback(false, "Error en servidor al enviar código")
                }
            }
            override fun onFailure(call: Call<clsDatosRespuesta>, t: Throwable) {
                callback(false, t.message ?: "Error de red al enviar código")
            }
        })
    }

    // ---------------------------------------------------------
    // VERIFICAR Y GUARDAR USUARIO (Registro)
    // ---------------------------------------------------------
    fun verificarYGuardar(
        nombre: String, apPaterno: String, apMaterno: String,
        correo: String, usuario: String, password: String, // Aquí usas 'usuario'
        rol: String, codigo: String, callback: (Boolean, String?) -> Unit
    ) {
        api.registrarUsuario(
            nombre, apPaterno, apMaterno, correo,
            usuario, password, rol, codigo // Asegúrate de pasar la variable correcta
        ).enqueue(object : Callback<clsDatosRespuesta> {

            override fun onResponse(call: Call<clsDatosRespuesta>, response: Response<clsDatosRespuesta>) {

                // Si Retrofit logra parsear el JSON (código 200 OK)
                response.body()?.let { body ->
                    Log.d("API-BODY", body.toString())
                    callback(body.Estado == "true", body.Salida)
                    return
                }

                // Si hay un error HTTP (404, 500) o JSON inválido
                val errorText = response.errorBody()?.string()
                if (!errorText.isNullOrEmpty()) {
                    Log.e("API-RAW", "Error ${response.code()}: $errorText")
                    // Intentamos dar un mensaje más descriptivo si es un 500 vacío
                    if (response.code() == 500 && errorText.isEmpty()) {
                        callback(false, "Error 500: Fallo interno del servidor (Script PHP muerto)")
                        return
                    }
                    callback(false, "Respuesta no válida del servidor")
                    return
                }

                // Esto solo debería pasar si la respuesta es 204 (No Content) o similar
                callback(false, "Servidor no devolvió JSON")
            }

            override fun onFailure(call: Call<clsDatosRespuesta>, t: Throwable) {
                Log.e("API-FAILURE", "Error de conexión: ${t.message}")
                callback(false, t.message)
            }
        })
    }
}