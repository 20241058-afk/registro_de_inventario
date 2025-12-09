package com.example.registrodeinventario

import android.util.Log
import com.example.registrodeinventario.Modelo.Categoria
import com.example.registrodeinventario.Modelo.Color
import com.example.registrodeinventario.Modelo.Marca
import com.example.registrodeinventario.Modelo.clsEquipos
import com.example.registrodeinventario.Modelo.ifaceApiService
import com.example.registrodeinventario.Vista.clsDatosRespuesta
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Test {

    private val retrofit= Retrofit.Builder()
        .baseUrl("https://inventariocomputo.grupoctic.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val flor=retrofit.create(ifaceApiService::class.java)

    fun probarMarca() {
        flor.obtenerMarca().enqueue(object : Callback<List<Marca>> {

            override fun onResponse(
                call: Call<List<Marca>>,
                response: Response<List<Marca>>
            ) {
                Log.d("PRUEBA_RETROFIT", "LISTAR => ${response.body()}")
            }

            override fun onFailure(call: Call<List<Marca>>, t: Throwable) {
                Log.e("PRUEBA_RETROFIT", "ERROR => ${t.message}")
            }
        })
    }

    fun probarLogin(email: String, password: String) {
        flor.iniciarSesion("login", email, password)
            .enqueue(object : Callback<List<clsDatosRespuesta>> {
                override fun onResponse(
                    call: Call<List<clsDatosRespuesta>>,
                    response: Response<List<clsDatosRespuesta>>
                ) {
                    Log.d("PRUEBA_API", "Login => ${response.body()}")
                }

                override fun onFailure(call: Call<List<clsDatosRespuesta>>, t: Throwable) {
                    Log.e("PRUEBA_API", "Error login => ${t.message}")
                }
            })
    }

    fun probarObtenerEquipos() {
        flor.obtenerEquipos().enqueue(object : Callback<List<clsEquipos>> {
            override fun onResponse(
                call: Call<List<clsEquipos>>,
                response: Response<List<clsEquipos>>
            ) {
                Log.d("PRUEBA_API", "Equipos => ${response.body()}")
            }

            override fun onFailure(call: Call<List<clsEquipos>>, t: Throwable) {
                Log.e("PRUEBA_API", "Error equipos => ${t.message}")
            }
        })
    }

    fun probarGuardarCategoria(nombre: String) {
        flor.guardarCategoria(nombre = nombre)
            .enqueue(object : Callback<List<clsDatosRespuesta>> {
                override fun onResponse(
                    call: Call<List<clsDatosRespuesta>>,
                    response: Response<List<clsDatosRespuesta>>
                ) {
                    Log.d("PRUEBA_API", "Guardar categoría => ${response.body()}")
                }

                override fun onFailure(
                    call: Call<List<clsDatosRespuesta>>,
                    t: Throwable
                ) {
                    Log.e("PRUEBA_API", "Error categoría => ${t.message}")
                }
            })
    }

    fun probarGuardarMarca(nombre: String) {
        flor.guardarMarca(nombre = nombre)
            .enqueue(object : Callback<List<clsDatosRespuesta>> {
                override fun onResponse(
                    call: Call<List<clsDatosRespuesta>>,
                    response: Response<List<clsDatosRespuesta>>
                ) {
                    Log.d("PRUEBA_API", "Guardar marca => ${response.body()}")
                }

                override fun onFailure(
                    call: Call<List<clsDatosRespuesta>>,
                    t: Throwable
                ) {
                    Log.e("PRUEBA_API", "Error marca => ${t.message}")
                }
            })
    }

    fun probarObtenerRoles() {
        flor.obtenerRoles().enqueue(object : Callback<List<String>> {
            override fun onResponse(
                call: Call<List<String>>,
                response: Response<List<String>>
            ) {
                Log.d("PRUEBA_API", "Roles => ${response.body()}")
            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                Log.e("PRUEBA_API", "Error roles => ${t.message}")
            }
        })
    }

    fun probarEnviarCodigo(correo: String) {
        flor.enviarCodigo(correo).enqueue(object : Callback<clsDatosRespuesta> {
            override fun onResponse(
                call: Call<clsDatosRespuesta>,
                response: Response<clsDatosRespuesta>
            ) {
                Log.d("PRUEBA_API", "Enviar código => ${response.body()}")
            }

            override fun onFailure(call: Call<clsDatosRespuesta>, t: Throwable) {
                Log.e("PRUEBA_API", "Error código => ${t.message}")
            }
        })
    }

    fun probarRegistrarUsuario(
        nombre: String,
        apPat: String,
        apMat: String,
        correo: String,
        usuario: String,
        password: String,
        rol: String,
        codigo: String
    ) {
        flor.registrarUsuario(nombre, apPat, apMat, correo, usuario, password, rol, codigo)
            .enqueue(object : Callback<clsDatosRespuesta> {
                override fun onResponse(
                    call: Call<clsDatosRespuesta>,
                    response: Response<clsDatosRespuesta>
                ) {
                    Log.d("PRUEBA_API", "Registrar usuario => ${response.body()}")
                }

                override fun onFailure(call: Call<clsDatosRespuesta>, t: Throwable) {
                    Log.e("PRUEBA_API", "Error registrar usuario => ${t.message}")
                }
            })
    }

    fun probarObtenerColor() {
        flor.obtenerColor().enqueue(object : Callback<List<Color>> {
            override fun onResponse(
                call: Call<List<Color>>,
                response: Response<List<Color>>
            ) {
                Log.d("PRUEBA_API", "Colores => ${response.body()}")
            }

            override fun onFailure(call: Call<List<Color>>, t: Throwable) {
                Log.e("PRUEBA_API", "Error color => ${t.message}")
            }
        })
    }

    fun probarObtenerCategoriaInv() {
        flor.obtenerCategoria().enqueue(object : Callback<List<Categoria>> {
            override fun onResponse(
                call: Call<List<Categoria>>,
                response: Response<List<Categoria>>
            ) {
                Log.d("PRUEBA_API", "Categorías inventario => ${response.body()}")
            }

            override fun onFailure(call: Call<List<Categoria>>, t: Throwable) {
                Log.e("PRUEBA_API", "Error categoría inventario => ${t.message}")
            }
        })
    }

    fun probarGuardarInventario(
        numInv: String,
        numSerie: String,
        categoria: String,
        marca: String,
        color: String
    ) {
        flor.guardarInventario(
            numInv = numInv,
            numSerie = numSerie,
            categoria = categoria,
            marca = marca,
            color = color
        ).enqueue(object : Callback<clsDatosRespuesta> {
            override fun onResponse(
                call: Call<clsDatosRespuesta>,
                response: Response<clsDatosRespuesta>
            ) {
                Log.d("PRUEBA_API", "Guardar inventario => ${response.body()}")
            }

            override fun onFailure(call: Call<clsDatosRespuesta>, t: Throwable) {
                Log.e("PRUEBA_API", "Error inventario => ${t.message}")
            }
        })
    }
}