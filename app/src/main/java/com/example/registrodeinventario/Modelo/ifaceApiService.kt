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

    //Categoria
    @FormUrlEncoded
    @POST("apicategoria.php")
    fun guardarCategoria(
        @Field("action") action: String = "guardar_categoria",
        @Field("nombre") nombre: String
    ): Call<List<clsDatosRespuesta>>

    //Marca
    @FormUrlEncoded
    @POST("apiMarca.php")
    fun guardarMarca(
        @Field("action") action: String = "guardar_Marca",
        @Field("nombre") nombre: String
    ): Call<List<clsDatosRespuesta>>

    //Agregrar registro
    @FormUrlEncoded
    @POST("apiEncargado.php")
    fun guardarEncargado(
        @Field("action") action: String = "guardar_encargado",
        @Field("nombre") nombre: String,
        @Field("ap_paterno") apPaterno: String,
        @Field("ap_materno") apMaterno: String,
        @Field("correo") correo: String,
        @Field("matricula") matricula: String,
        @Field("password") password: String,
        @Field("codigo_verificacion") codigoV: String,
        @Field("rol") rol: String
    ): Call<List<clsDatosRespuesta>>

    //Obtener roles
    @GET("apiEncargado.php?action=obtener_roles")
    fun obtenerRoles(): Call<List<Rol>>



}