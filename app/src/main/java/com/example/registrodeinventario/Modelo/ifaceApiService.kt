package com.example.registrodeinventario.Modelo

import com.example.registrodeinventario.Vista.clsDatosRespuesta
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ifaceApiService {

    @POST("apiEquipos.php")
    @FormUrlEncoded
    fun obtenerEquipos(
        @Field("action") action: String = "obtener_todos"
    ): Call<List<clsEquipos>>

    //Para login
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


    // Obtener roles
    @GET("apiEncargado.php?action=obtener_roles")
    fun obtenerRoles(): Call<List<String>>

    // Enviar código de verificación
    @FormUrlEncoded
    @POST("enviar_codigo.php")
    fun enviarCodigo(
        @Field("correo") correo: String
    ): Call<clsDatosRespuesta>

    // Registrar usuario (la API que falla con el 500)
    @FormUrlEncoded
    @POST("registrar_usuario.php")
    fun registrarUsuario(
        @Field("nombre") nombre: String,
        @Field("ap_paterno") apPaterno: String,
        @Field("ap_materno") apMaterno: String,
        @Field("correo") correo: String,
        @Field("usuario") usuario: String,
        @Field("password") password: String,
        @Field("rol") rol: String,
        @Field("codigo") codigoVerificacion: String
    ): Call<clsDatosRespuesta>

    @GET("apiInventario.php?action=obtener_color")
    fun obtenerColor(): Call<List<Color>>

    @GET("apiInventario.php?action=obtener_marca")
    fun obtenerMarca(): Call<List<Marca>>

    @GET("apiInventario.php?action=obtener_categoria")
    fun obtenerCategoria(): Call<List<Categoria>>

    @FormUrlEncoded
    @POST("apiInventario.php")
    fun guardarInventario(
        @Field("action") action: String = "guardar_inventario",
        @Field("num_inventario") numInv: String,
        @Field("num_serie") numSerie: String,
        @Field("nombre_categoria") categoria: String,
        @Field("nombre_marca") marca: String,
        @Field("nombre_color") color: String
    ): Call<clsDatosRespuesta>



    @FormUrlEncoded
    @POST("apiLogin.php") // cambia por la ruta real de tu login
    fun login(
        @Field("usuario") usuario: String,
        @Field("password") password: String
    ): Call<clsUsuario>

    data class HistorialResponse(
        val Estado: String,
        val Historial: List<clsHistorial>
    )

    @GET("apiHistorial.php")
    suspend fun obtenerHistorial(@Query("id_usuario") idUsuario: Int): Response<clsRespuestaHistorial>



}
