package com.example.registrodeinventario.Modelo

import com.example.registrodeinventario.Vista.clsDatosRespuesta
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ifaceApiService {
    @POST("apiEquipos.php")
    @FormUrlEncoded
    fun obtenerEquipos(
        @Field("action") action: String = "obtener_todos"
    ): Call<List<clsEquipos>>

    //Para login y registro
    @FormUrlEncoded
    @POST("apiAcceso.php")
    fun registrarUsuario(
        @Field("action") action: String,
        @Field("nombreUsuario") nombreusuario: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<List<clsDatosRespuesta>>

    @FormUrlEncoded
    @POST("apiAcceso.php")
    fun iniciarSesion(
        @Field("action") action: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<List<clsDatosRespuesta>>
}